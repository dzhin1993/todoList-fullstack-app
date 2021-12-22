package com.zhinkoilya1993.backend.to;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterRequest extends LoginRequest {

    @NotBlank
    @Size(min = 5, max = 100)
    private String passwordConfirm;

    public RegisterRequest(String password, String passwordConfirm, String email) {
        super(password, email);
        this.passwordConfirm = passwordConfirm;
    }
}
