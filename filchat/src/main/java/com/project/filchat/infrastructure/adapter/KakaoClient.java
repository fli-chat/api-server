package com.project.filchat.infrastructure.adapter;

import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.filchat.application.auth.OAuthClient;
import com.project.filchat.common.exception.ErrorCode;
import com.project.filchat.common.exception.InternalServerException;
import com.project.filchat.domain.auth.OAuthProvider;
import com.project.filchat.domain.auth.OAuthUser;
import com.project.filchat.domain.user.User;
import com.project.filchat.infrastructure.config.properties.OAuthProperties;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KakaoClient implements OAuthClient {
    private final OAuthProperties oAuthProperties;
    private final RestTemplate restTemplate;

    @Override
    public boolean isSupport(OAuthProvider provider) {
        return OAuthProvider.KAKAO == provider;
    }

    @Override
    public String getAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<Object> request = new HttpEntity<>(createTokenRequestBody(code), headers);
        KakaoTokenResponse response = restTemplate.postForObject(
            oAuthProperties.kakao().serverInfo().authTokenUrl(),
            request,
            KakaoTokenResponse.class
        );

        return Optional.ofNullable(response)
            .orElseThrow(() -> new InternalServerException(ErrorCode.OAUTH_TOKEN_FETCH_FAILED))
            .accessToken();
    }

    private record KakaoTokenResponse(
        @JsonProperty("access_token") String accessToken
    ) {
    }

    private MultiValueMap<String, String> createTokenRequestBody(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", oAuthProperties.kakao().clientId());
        params.add("redirect_url", oAuthProperties.kakao().serverInfo().redirectUrl());
        params.add("code", code);
        params.add("client_secret", oAuthProperties.kakao().secretId());
        return params;
    }

    @Override
    public OAuthUser getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(accessToken);

        HttpEntity request = new HttpEntity<>(headers);
        KakaoUserResponse response = restTemplate.postForObject(
            oAuthProperties.kakao().serverInfo().resourceServerUrl(),
            request,
            KakaoUserResponse.class
        );

        return Optional.ofNullable(response)
            .map(res -> OAuthUser.of(res.id(), res.getGender(), res.getAgeGroup()))
            .orElseThrow(() -> new InternalServerException(ErrorCode.USER_INFO_FETCH_FAILED));
    }

    private record KakaoUserResponse(
        String id,
        String gender,
        String ageGroup
    ) {
        int getAgeGroup() {
            if (ageGroup == null) {
                return 10;
            }
            return Integer.parseInt(ageGroup.split("~")[1]) / 10 * 10;
        }

        User.Gender getGender() {
            if (gender == null || gender.equals("female")) {
                return User.Gender.WOMEN;
            }
            return User.Gender.MEN;
        }
    }
}
