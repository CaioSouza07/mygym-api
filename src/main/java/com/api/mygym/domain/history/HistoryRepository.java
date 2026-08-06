package com.api.mygym.domain.history;

import com.api.mygym.domain.exercise.Exercise;
import com.api.mygym.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<History, UUID> {
    List<History> findAllByUser(User user);
    Page<History> findAllByUserAndExercise(User user, Exercise exercise, Pageable pageable);
}
