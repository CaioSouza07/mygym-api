package com.api.mygym.domain.serie.dto;

import com.api.mygym.domain.serie.Serie;

import java.util.UUID;

public record SerieResponse(
        UUID id,
        int order,
        int repetitions
) {
    public SerieResponse(Serie data){
        this(data.getId(), data.getOrder(), data.getRepetitions());
    }
}
