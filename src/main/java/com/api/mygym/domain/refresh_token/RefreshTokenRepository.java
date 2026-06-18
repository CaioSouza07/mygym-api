package com.api.mygym.domain.refresh_token;

import com.api.mygym.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByToken(String token);
    List<RefreshToken> findAllByUser(User user);

    @Modifying
    @Query("""
        DELETE FROM RefreshToken rt
        WHERE rt.revoked = true
           OR rt.expiresAt < CURRENT_TIMESTAMP
    """)
    void deleteExpiredAndRevokedTokens();
}
