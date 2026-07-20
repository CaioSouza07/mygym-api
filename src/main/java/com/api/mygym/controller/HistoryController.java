package com.api.mygym.controller;

import com.api.mygym.domain.history.HistoryService;
import com.api.mygym.domain.history.dto.HistoryRequest;
import com.api.mygym.domain.history.dto.HistoryResponse;
import com.api.mygym.domain.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @PostMapping
    public ResponseEntity<Void> save(
            @RequestBody @Valid HistoryRequest requestBody,
            @AuthenticationPrincipal User user,
            UriComponentsBuilder uriBuilder
    ){
        var uri = uriBuilder.path("/history").buildAndExpand().toUri();

        historyService.save(requestBody, user);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<HistoryResponse>> getAll(@AuthenticationPrincipal User user){
        var history = historyService.getAll(user);
        return ResponseEntity.ok(history);
    }
}
