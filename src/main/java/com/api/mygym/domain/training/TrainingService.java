package com.api.mygym.domain.training;

import com.api.mygym.domain.training.dto.TrainingRequest;
import com.api.mygym.domain.training.dto.TrainingResponse;
import com.api.mygym.domain.user.User;
import com.api.mygym.infra.exception.NotFoundException;
import com.api.mygym.infra.exception.TrainingAlreadyExistsForDayException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainingService {

    private final TrainingRepository repository;
    private final TrainingMapper mapper;

    @Transactional
    public TrainingResponse add(TrainingRequest request, User user){

        if (repository.existsByUserIdAndWeekDay(user.getId(), request.weekDay())){
            throw new TrainingAlreadyExistsForDayException("Esse dia da semana já possuí um treino para esse usuário");
        }
        var training = mapper.toEntity(request, user);
        repository.save(training);

        return new TrainingResponse(training);
    }

    @Transactional
    public TrainingResponse update(UUID trainingId, TrainingRequest request, User user){

        var training = repository.findById(trainingId)
                .orElseThrow(() -> new NotFoundException("Treino não encontrado com esse id"));

        if (repository.existsByUserIdAndWeekDayAndIdNot(user.getId(), request.weekDay(), trainingId)){
            throw new TrainingAlreadyExistsForDayException("Esse dia da semana já possuí um treino para esse usuário");
        }

        mapper.updateEntity(training, request);

        return new TrainingResponse(training);
    }

    @Transactional
    public void remove(UUID id){

        var training = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Treino não encontrado com esse id"));

        repository.delete(training);
    }

    public List<TrainingResponse> getAllByUser(User user){

        var trainings = repository.findAllByUserId(user.getId());

        return trainings.stream()
                .map(TrainingResponse::new)
                .toList();
    }
}
