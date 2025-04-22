package com.project.filchat.domain.user;

import java.util.UUID;

import com.project.filchat.common.util.TokenGenerator;
import com.project.filchat.domain.AbstractEntity;
import com.project.filchat.domain.auth.OAuthProvider;
import com.project.filchat.interfaces.auth.AuthDto;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends AbstractEntity {
    private static final String PREFIX_USER = "user_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userToken;
    private String nickname;
    private String profileUrl;
    private String identifier;
    private boolean isActive = false;

    @Enumerated(EnumType.STRING)
    private OAuthProvider provider;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.ORDINAL)
    private AgeGroup ageGroup;

    @Getter
    @RequiredArgsConstructor
    public enum Gender {
        MEN('M'),
        WOMEN('W');

        private final char gender;
    }

    @Getter
    @RequiredArgsConstructor
    public enum AgeGroup {
        TEENS((byte)1),
        TWENTIES((byte)2),
        THIRTIES((byte)3),
        FORTIES((byte)4),
        FIFTIES((byte)5);

        private final byte age;

        public static AgeGroup fromAge(int age) {
            if (age >= 10 && age < 20)
                return TEENS;
            if (age >= 20 && age < 30)
                return TWENTIES;
            if (age >= 30 && age < 40)
                return THIRTIES;
            if (age >= 40 && age < 50)
                return FORTIES;
            return FIFTIES;
        }
    }

    @Builder
    public User(AuthDto.LoginRequest request, OAuthProvider provider) {
        this.userToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_USER);
        this.nickname = UUID.randomUUID().toString().substring(0, 29);
        this.gender = request.gender();
        this.ageGroup = AgeGroup.fromAge(request.ageGroup());
        this.identifier = request.identifier();
        this.provider = provider;
    }
}
