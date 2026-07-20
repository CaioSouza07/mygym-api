package com.api.mygym.domain.history;

import com.api.mygym.domain.exercise.ExerciseRepository;
import com.api.mygym.domain.history.dto.ExerciseHistoryResponse;
import com.api.mygym.domain.history.dto.HistoryRequest;
import com.api.mygym.domain.user.User;
import com.api.mygym.infra.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final ExerciseRepository exerciseRepository;

    @Transactional
    public ExerciseHistoryResponse save(HistoryRequest data, User user){

        var exercise = exerciseRepository.findById(data.exerciseId())
                .orElseThrow(() -> new NotFoundException("Exercício não encontrado"));

        var history = new History(exercise, data.weight(), user);
        historyRepository.save(history);

        return new ExerciseHistoryResponse(history);
    }


}
