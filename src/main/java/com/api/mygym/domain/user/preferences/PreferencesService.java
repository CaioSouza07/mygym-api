package com.api.mygym.domain.user.preferences;

import com.api.mygym.domain.user.User;
import com.api.mygym.infra.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PreferencesService {

    private final PreferencesRepository preferencesRepository;

    @Transactional
    @Async
    public void addPreferences(User user){
        var preferences = new Preferences(user);
        preferencesRepository.save(preferences);
    }

    @Transactional
    public Preferences updatePreferences(User user, Integer defaultRestTime){
        var preferences = preferencesRepository.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Preferências de usuário não encontrado"));
        preferences.setDefaultRestTime(defaultRestTime);
        return preferences;
    }

    public Preferences getPreferences(User user){
        return preferencesRepository.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Preferências de usuário não encontrado"));
    }
}
