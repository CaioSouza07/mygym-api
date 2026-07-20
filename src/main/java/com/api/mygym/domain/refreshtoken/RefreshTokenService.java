package com.api.mygym.domain.refreshtoken;

import com.api.mygym.domain.user.User;
import com.api.mygym.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenService tokenService;

    @Transactional
    public RefreshToken create(User user){
        var refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString().replace("-", ""));
        refreshToken.setExpiresAt(Instant.now().plus(30, ChronoUnit.DAYS));
        refreshToken.setRevoked(false);
        refreshToken.setUser(user);

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken validate(String token){
        var refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new AccessDeniedException("Refresh token inválido"));

        if (refreshToken.isRevoked()){
            throw new AccessDeniedException("Refresh token revogado");
        }

        if (refreshToken.getExpiresAt().isBefore(Instant.now())){
            throw new AccessDeniedException("Refresh token expirado");
        }

        return refreshToken;
    }

    @Transactional
    public void revoke(RefreshToken refreshToken){
        refreshToken.setRevoked(true);
    }

    @Transactional
    public void revokeByToken(String token){
        var refreshToken = validate(token);
        refreshToken.setRevoked(true);
    }

    @Transactional
    public void revokeAll(User user){
        var tokens = refreshTokenRepository.findAllByUser(user);
        tokens.forEach(token -> token.setRevoked(true));
    }

    public String generateNewAccessToken(String refreshTokenValue){
        var refreshToken = validate(refreshTokenValue);
        var user = refreshToken.getUser();
        return tokenService.generateToken(user);
    }
}
