package com.zhinkoilya1993.backend.service;

import com.zhinkoilya1993.backend.LoggedUser;
import com.zhinkoilya1993.backend.config.security.jwt.JwtProvider;
import com.zhinkoilya1993.backend.exception.PasswordNotMatchesException;
import com.zhinkoilya1993.backend.exception.UserExistException;
import com.zhinkoilya1993.backend.model.User;
import com.zhinkoilya1993.backend.repository.UserRepository;
import com.zhinkoilya1993.backend.to.LoginRequest;
import com.zhinkoilya1993.backend.to.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    public UserService(UserRepository repository, PasswordEncoder encoder, JwtProvider jwtProvider) {
        this.repository = repository;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        if (user != null) {
            return new LoggedUser(user);
        }
        throw new UsernameNotFoundException("User '" + email + "' not found");
    }

    public String findAndGetToken(LoginRequest request) {
        UserDetails userDetails = findByLoginAndPassword(request.getEmail(), request.getPassword());
        return jwtProvider.generateJwtToken(userDetails.getUsername());
    }

    public void saveUser(RegisterRequest registerRequest) {
        if (!registerRequest.getPassword().equals(registerRequest.getPasswordConfirm())) {
            throw new PasswordNotMatchesException("passwords not matches");
        }
        checkAndSave(new User(registerRequest));
    }

    public UserDetails findByLoginAndPassword(String login, String password) {
        UserDetails userDetails = loadUserByUsername(login);
        if (!encoder.matches(password, userDetails.getPassword())) {
            throw new PasswordNotMatchesException("password is wrong");
        }
        return userDetails;
    }

    private void checkAndSave(User user) {
        if (repository.findByEmail(user.getEmail()) != null) {
            throw new UserExistException("User with this email is already exist");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }
}
