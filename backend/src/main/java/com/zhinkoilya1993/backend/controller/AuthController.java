package com.zhinkoilya1993.backend.controller;

import com.zhinkoilya1993.backend.service.UserService;
import com.zhinkoilya1993.backend.to.LoginRequest;
import com.zhinkoilya1993.backend.to.RegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = AuthController.URL)
public class AuthController {

    static final String URL = "/api/auth/";

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("signin")
    public String loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        return service.findAndGetToken(loginRequest);
    }

    @PostMapping("signup")
    public void registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        service.saveUser(registerRequest);
    }
}
