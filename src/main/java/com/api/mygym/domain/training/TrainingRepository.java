package com.api.mygym.domain.training;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrainingRepository extends JpaRepository<Training, UUID> {
    boolean existsByUserIdAndWeekDay(UUID userId, WeekDay weekDay);
    List<Training> findAllByUserId(UUID id);
    boolean existsByUserIdAndWeekDayAndIdNot(UUID id, WeekDay weekDay, UUID trainingId);
    boolean existsByIdAndUserId(UUID id, UUID userId);
}
