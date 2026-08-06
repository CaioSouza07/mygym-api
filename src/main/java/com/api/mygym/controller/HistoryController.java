package com.api.mygym.controller;

import com.api.mygym.domain.history.HistoryService;
import com.api.mygym.domain.history.dto.ExerciseHistoryResponse;
import com.api.mygym.domain.history.dto.HistoryRequest;
import com.api.mygym.domain.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @PostMapping
    public ResponseEntity<Void> save(
            @RequestBody @Valid HistoryRequest requestBody,
            @AuthenticationPrincipal User user
    ){
        historyService.save(requestBody, user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity<Page<ExerciseHistoryResponse>> getAllByExerciseId(
            @PathVariable UUID exerciseId,
            @AuthenticationPrincipal User user,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
            ){
        var response = historyService.getAllByExerciseId(exerciseId, pageable, user);
        return ResponseEntity.ok(response);
    }
}
