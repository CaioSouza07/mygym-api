package com.api.mygym.domain.user.preferences.dto;

import com.api.mygym.domain.user.preferences.Preferences;
import jakarta.validation.constraints.NotNull;

public record PreferencesDTO(

        @NotNull
        Integer defaultRestTime
) {
        public PreferencesDTO(Preferences preferences){
                this(preferences.getDefaultRestTime());
        }
}
