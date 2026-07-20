package com.api.mygym.domain.history;

import com.api.mygym.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<History, UUID> {
    void deleteByExerciseId(UUID exerciseId);

    List<History> findAllByUser(User user);
}
