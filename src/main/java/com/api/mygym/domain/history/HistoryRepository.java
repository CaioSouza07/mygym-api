package com.api.mygym.domain.history;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HistoryRepository extends JpaRepository<History, UUID> {
    void deleteByExerciseId(UUID exerciseId);
}
