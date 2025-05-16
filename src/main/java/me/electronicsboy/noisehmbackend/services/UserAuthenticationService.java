package me.electronicsboy.noisehmbackend.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import me.electronicsboy.noisehmbackend.data.PrivilegeLevel;
import me.electronicsboy.noisehmbackend.dtos.LoginUserDto;
import me.electronicsboy.noisehmbackend.dtos.RegisterUserDto;
import me.electronicsboy.noisehmbackend.models.User;
import me.electronicsboy.noisehmbackend.repositories.UserRepository;

@Service
public class UserAuthenticationService {
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public UserAuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user = new User()
                .setFullname(input.getFullname())
                .setEmail(input.getEmail())
                .setUsername(input.getUsername())
                .setPrivilageLevel(PrivilegeLevel.CITIZEN)
                .setEnabled(true)
                .setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }
    
    public String getEncodedPassword(String plaintest) {
    	return passwordEncoder.encode(plaintest);
    }
}
