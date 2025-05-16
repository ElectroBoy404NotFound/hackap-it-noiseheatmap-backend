package me.electronicsboy.noisehmbackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.electronicsboy.noisehmbackend.models.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUsername(String username);
}
