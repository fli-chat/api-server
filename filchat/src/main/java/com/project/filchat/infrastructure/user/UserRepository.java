package com.project.filchat.infrastructure.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.filchat.domain.auth.OAuthProvider;
import com.project.filchat.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdentifierAndProvider(String identifier, OAuthProvider provider);
}
