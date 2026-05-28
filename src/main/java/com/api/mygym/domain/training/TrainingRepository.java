package com.api.mygym.domain.training;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrainingRepository extends JpaRepository<Training, UUID> {
    boolean existsByUserIdAndWeekDay(UUID id, WeekDay weekDay);
    List<Training> findAllByUserId(UUID id);
}
