package com.api.mygym.domain.user;

import com.api.mygym.domain.user.dto.CreateUserRequest;
import com.api.mygym.domain.user.dto.UserLoginRequest;
import com.api.mygym.domain.user.dto.UserResponse;
import com.api.mygym.infra.email.EmailService;
import com.api.mygym.infra.exception.UserAlreadyExistsException;
import com.api.mygym.infra.security.AuthResponse;
import com.api.mygym.infra.security.TokenService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse register(CreateUserRequest data){
        if (userRepository.findByEmail(data.email()) != null){
            throw new UserAlreadyExistsException("Usuário já cadastrado");
        }

        var encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        var user = new User(data, encryptedPassword);

        userRepository.save(user);

        try {
            emailService.sendWelcomeEmail(user.getEmail(), user.getName());
        } catch (MessagingException e) {
            System.err.println("Erro ao enviar o email: " + e.getMessage());
        }

        var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJwt = tokenService.generateToken((User) authentication.getPrincipal());
        return new AuthResponse(tokenJwt, new UserResponse(user));
    }

    public AuthResponse login(UserLoginRequest data){
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJwt = tokenService.generateToken((User) authentication.getPrincipal());
        return new AuthResponse(tokenJwt, new UserResponse((User) authentication.getPrincipal()));
    }
}
