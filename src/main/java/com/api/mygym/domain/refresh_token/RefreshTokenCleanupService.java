package com.api.mygym.domain.refresh_token;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenCleanupService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    @Scheduled(cron = "0 0 3 * * SUN")
    public void cleanup() {

        refreshTokenRepository.deleteExpiredAndRevokedTokens();

        System.out.println("Limpeza de refresh tokens executada");
    }
}
