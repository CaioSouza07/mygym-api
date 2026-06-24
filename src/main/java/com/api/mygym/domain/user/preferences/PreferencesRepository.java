package com.api.mygym.domain.user.preferences;

import com.api.mygym.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PreferencesRepository extends JpaRepository<Preferences, UUID> {
    Optional<Preferences> findByUser(User user);
}
