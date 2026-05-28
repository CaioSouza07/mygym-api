package com.api.mygym.domain.training;

import com.api.mygym.domain.training.dto.CreateTrainingRequest;
import com.api.mygym.domain.training.dto.TrainingResponse;
import com.api.mygym.domain.user.User;
import com.api.mygym.infra.exception.TrainingAlreadyExistsForDayException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainingService {

    private final TrainingRepository repository;

    public TrainingResponse add(CreateTrainingRequest request, User user){

        if (repository.existsByUserIdAndWeekDay(user.getId(), request.weekDay())){
            throw new TrainingAlreadyExistsForDayException("Esse dia da semana já possuí um treino para esse usuário");
        }

        var training = new Training(request, user);
        repository.save(training);

        return new TrainingResponse(training);
    }
}
