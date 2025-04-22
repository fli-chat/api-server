package com.project.filchat.application.auth;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.filchat.domain.auth.OAuthProvider;
import com.project.filchat.domain.user.User;
import com.project.filchat.infrastructure.jwt.JwtProvider;
import com.project.filchat.infrastructure.user.UserRepository;
import com.project.filchat.interfaces.auth.AuthDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Transactional
    public AuthDto.LoginResponse login(AuthDto.LoginRequest request, OAuthProvider provider) {
        // String accessToken = oAuthClient.getAccessToken(request.code());
        // OAuthUser oAuthUser = oAuthClient.getUserInfo(accessToken);

        Optional<User> user = userRepository.findByIdentifierAndProvider(
            request.identifier(), provider);

        if (user.isPresent()) {
            return createJwt(user.get());
        }
        return signup(request, provider);
    }

    private AuthDto.LoginResponse signup(AuthDto.LoginRequest request, OAuthProvider provider) {
        User user = User.builder()
            .request(request)
            .provider(provider)
            .build();
        userRepository.save(user);
        AuthDto.TokenResponse tokenResponse = jwtProvider.createJwtToken(user.getUserToken(), user.getNickname());
        return new AuthDto.LoginResponse(tokenResponse, true);
    }

    private AuthDto.LoginResponse createJwt(User user) {
        AuthDto.TokenResponse tokenResponse = jwtProvider.createJwtToken(user.getUserToken(), user.getNickname());
        return new AuthDto.LoginResponse(tokenResponse, false);
    }
}
