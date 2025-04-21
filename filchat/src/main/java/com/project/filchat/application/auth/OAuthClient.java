package com.project.filchat.application.auth;

import com.project.filchat.domain.auth.OAuthProvider;
import com.project.filchat.domain.auth.OAuthUser;

public interface OAuthClient {
    boolean isSupport(OAuthProvider provider);

    String getAccessToken(String code);

    OAuthUser getUserInfo(String accessToken);
}
