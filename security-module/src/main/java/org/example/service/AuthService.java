package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.AuthRequest;
import org.example.entity.User;
import org.example.exception.CredFailedException;
import org.example.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtUtils;

    private final JwtValidator jwtValidator;

    public String saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "user added to the system";
    }

    public String generateJwtToken(AuthRequest authRequest) {
        System.out.println(userRepository.findByFirstName(authRequest.getUserName()));
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if(authentication.isAuthenticated()) {
//            return jwtUtils.generateJwtToken(authRequest.getUserName());
            return jwtUtils.generateJwtToken(authRequest.getUserName());
        } else {
            System.out.println("не авторизирован");
            throw new CredFailedException();
        }
    }

    public boolean validateToken(String token) {
        return jwtValidator.validationToken(token);
    }

}
