package com.zhinkoilya1993.backend;

import com.zhinkoilya1993.backend.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class LoggedUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public LoggedUser(User user) {
        super(user.getEmail(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        this.user = user;
    }

    public int getId() {
        return user.getId();
    }
}
