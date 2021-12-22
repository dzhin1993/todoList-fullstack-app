package com.zhinkoilya1993.backend.exception;

import com.zhinkoilya1993.backend.to.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserExistException.class)
    public ErrorMessage userExistError(UserExistException e) {
        return new ErrorMessage(HttpStatus.CONFLICT, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordNotMatchesException.class)
    public ErrorMessage passwordsNotMatchesError(PasswordNotMatchesException e) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorMessage userValidationError(BindException e) {
        String errors = e.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("\n"));
        return new ErrorMessage(HttpStatus.BAD_REQUEST, errors);
    }
}
