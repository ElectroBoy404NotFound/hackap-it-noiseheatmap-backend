package me.electronicsboy.noisehmbackend.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.electronicsboy.noisehmbackend.dtos.LoginUserDto;
import me.electronicsboy.noisehmbackend.dtos.RefreshDto;
import me.electronicsboy.noisehmbackend.dtos.RegisterUserDto;
import me.electronicsboy.noisehmbackend.exceptions.InvalidRefreshTokenException;
import me.electronicsboy.noisehmbackend.models.RefreshToken;
import me.electronicsboy.noisehmbackend.models.User;
import me.electronicsboy.noisehmbackend.repositories.UserRepository;
import me.electronicsboy.noisehmbackend.responses.LoginResponse;
import me.electronicsboy.noisehmbackend.responses.LogoutResponse;
import me.electronicsboy.noisehmbackend.services.JwtService;
import me.electronicsboy.noisehmbackend.services.RefreshTokenService;
import me.electronicsboy.noisehmbackend.services.UserAuthenticationService;

@RequestMapping("/userauth")
@RestController
public class UserAuthenticationController {
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserAuthenticationService authenticationService;
    private final UserRepository userRepository;

    public UserAuthenticationController(JwtService jwtService, UserAuthenticationService authenticationService, RefreshTokenService refreshTokenService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.refreshTokenService = refreshTokenService;
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
    	User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser, "user");
        String jwtRefreshToken = refreshTokenService.createRefreshToken(authenticatedUser.getUsername()).getToken();

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setRefreshToken(jwtRefreshToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshDto request) {
        String refreshTokenStr = request.getRefreshToken();
        
        Optional<RefreshToken> optionalToken = refreshTokenService.verifyToken(refreshTokenStr);

        if (optionalToken.isPresent()) {
            RefreshToken rt = optionalToken.get();
            User user = userRepository.findByUsername(rt.getUsername()).orElseThrow();
            String newAccessToken = jwtService.generateToken(user, "user");
            return ResponseEntity.ok(new LoginResponse().setToken(newAccessToken).setRefreshToken(null).setExpiresIn(jwtService.getExpirationTime()));
        }
        throw new InvalidRefreshTokenException("Invalid refresh token!");    
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	User user = (User) authentication.getPrincipal();
        refreshTokenService.deleteByUsername(user.getUsername());
        return ResponseEntity.ok(new LogoutResponse().setStatus(0));
    }
}
