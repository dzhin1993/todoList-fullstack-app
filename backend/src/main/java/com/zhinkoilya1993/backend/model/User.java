package com.zhinkoilya1993.backend.model;

import com.zhinkoilya1993.backend.to.LoginRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractBaseEntity {

    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    public User(LoginRequest loginRequest) {
        this.email = loginRequest.getEmail();
        this.password = loginRequest.getPassword();
    }

    public User(Integer id, String password, String email) {
        this.setId(id);
        this.password = password;
        this.email = email;
    }
}
