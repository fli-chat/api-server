package com.project.filchat.domain.auth;

import com.project.filchat.domain.user.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OAuthUser {
    private final String identifier;
    private final User.Gender gender;
    private final int ageGroup;

    public static OAuthUser of(String identifier, User.Gender gender, int ageGroup) {
        return new OAuthUser(identifier, gender, ageGroup);
    }
}
