package com.api.mygym.controller;

import com.api.mygym.domain.training.TrainingService;
import com.api.mygym.domain.training.dto.TrainingRequest;
import com.api.mygym.domain.training.dto.TrainingResponse;
import com.api.mygym.domain.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/training")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;

    @PostMapping
    public ResponseEntity<TrainingResponse> createTraining(
            @RequestBody @Valid TrainingRequest request,
            @AuthenticationPrincipal User user,
            UriComponentsBuilder uriBuilder
            ){
        var training = trainingService.add(request, user);
        var uri = uriBuilder.path("/training/{id}").buildAndExpand(training.id()).toUri();
        return ResponseEntity.created(uri).body(training);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingResponse> updateTraining(
            @PathVariable UUID id,
            @RequestBody @Valid TrainingRequest request,
            @AuthenticationPrincipal User user
    ){
        var training = trainingService.update(id, request, user);
        return ResponseEntity.ok(training);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraining(
            @PathVariable UUID id,
            @AuthenticationPrincipal User user
    ){
        trainingService.remove(id, user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TrainingResponse>> getAll(@AuthenticationPrincipal User user){
        var trainings = trainingService.getAllByUser(user);
        return ResponseEntity.ok(trainings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingResponse> getById(@PathVariable UUID id){
        var training = trainingService.getById(id);
        return ResponseEntity.ok(training);
    }
}
