package com.api.mygym.controller;

import com.api.mygym.domain.user.User;
import com.api.mygym.domain.user.UserRepository;
import com.api.mygym.domain.user.dto.CreateUserRequest;
import com.api.mygym.domain.user.dto.UserLoginRequest;
import com.api.mygym.domain.user.dto.UserResponse;
import com.api.mygym.infra.exception.UserAlreadyExistsException;
import com.api.mygym.infra.security.TokenResponse;
import com.api.mygym.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid UserLoginRequest request){

        var authenticationToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJwt = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenResponse(tokenJwt));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid CreateUserRequest request){
        System.out.println("ENTROU NO REGISTER");
        if (userRepository.findByEmail(request.email()) != null){
            throw new UserAlreadyExistsException("Usuário já cadastrado");
        }

        var encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
        var user = new User(request, encryptedPassword);

        userRepository.save(user);
        return ResponseEntity.ok(new UserResponse(user));
    }
}
