package org.example.controller;

import org.example.dto.AuthRequest;
import org.example.entity.User;
import org.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthService service;


    @PostMapping("/registry")
    public String addNewUser(@RequestBody User user) {
        return service.saveUser(user);
    }



    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        return service.generateJwtToken(authRequest);
    }


    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Токен успешно провалидирован";
    }

}
