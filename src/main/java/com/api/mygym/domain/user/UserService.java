package com.api.mygym.domain.user;

import com.api.mygym.domain.refresh_token.RefreshTokenService;
import com.api.mygym.domain.user.dto.*;
import com.api.mygym.infra.email.EmailService;
import com.api.mygym.infra.exception.BadRequestException;
import com.api.mygym.infra.exception.UserAlreadyExistsException;
import com.api.mygym.infra.security.AuthResponse;
import com.api.mygym.infra.security.CookieUtils;
import com.api.mygym.infra.security.TokenService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public AuthResponse register(CreateUserRequest data, HttpServletResponse response){
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
        var refreshToken = refreshTokenService.create((User) authentication.getPrincipal());
        response.addCookie(CookieUtils.createRefreshToken(refreshToken.getToken()));
        return new AuthResponse(tokenJwt, new UserResponse(user));
    }

    public AuthResponse login(UserLoginRequest data, HttpServletResponse response){
        try {
            var authenticationToken =
                    new UsernamePasswordAuthenticationToken(data.email(), data.password());

            var authentication = authenticationManager.authenticate(authenticationToken);

            var user = (User) authentication.getPrincipal();

            var tokenJwt = tokenService.generateToken(user);

            var refreshToken = refreshTokenService.create(user);
            response.addCookie(CookieUtils.createRefreshToken(refreshToken.getToken()));

            return new AuthResponse(tokenJwt, new UserResponse(user));

        } catch (Exception e) {
            throw new BadCredentialsException("E-mail ou senha incorretos");
        }
    }

    @Transactional

    public UserResponse updateUser(UpdateUserRequest data, User user){
        var managedUser = userRepository.findById(user.getId())
                .orElseThrow();
        managedUser.setName(data.name());
        return new UserResponse(managedUser);
    }


    @Transactional
    public void changePassword(ChangePasswordRequest data, User user, HttpServletResponse response) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(data.currentPassword(), user.getPassword())){
            throw new BadRequestException("Senha atual incorreta");
        }

        if (encoder.matches(data.newPassword(), user.getPassword())) {
            throw new BadRequestException("Nova senha não pode ser igual à atual");
        }

        var newEncryptedPassword = encoder.encode(data.newPassword());

        user.setPassword(newEncryptedPassword);

        refreshTokenService.revokeAll(user);

        var newRefreshToken = refreshTokenService.create(user);
        response.addCookie(CookieUtils.createRefreshToken(newRefreshToken.getToken()));
    }
}
