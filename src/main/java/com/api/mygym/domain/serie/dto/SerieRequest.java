package com.api.mygym.domain.serie.dto;

import jakarta.validation.constraints.NotNull;

public record SerieRequest(

        @NotNull
        int order,

        @NotNull
        int repetitions
) {
}
