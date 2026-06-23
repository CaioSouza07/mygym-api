package com.api.mygym.domain.user.preferences;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PreferencesRepository extends JpaRepository<Preferences, UUID> {
}
