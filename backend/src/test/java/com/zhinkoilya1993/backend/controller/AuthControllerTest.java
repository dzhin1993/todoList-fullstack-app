package com.zhinkoilya1993.backend.controller;

import com.zhinkoilya1993.backend.to.LoginRequest;
import com.zhinkoilya1993.backend.to.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static com.zhinkoilya1993.backend.controller.AuthController.URL;
import static com.zhinkoilya1993.backend.TestData.*;
import static com.zhinkoilya1993.backend.util.JsonUtil.writeValue;
import static com.zhinkoilya1993.backend.util.TestUtil.getContent;
import static com.zhinkoilya1993.backend.util.TestUtil.validateJwtToken;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest extends AbstractControllerTest {

    @Test
    public void loginUser() throws Exception {
        ResultActions resultActions = perform(post(URL + "signin")
                .contentType(APPLICATION_JSON)
                .content(writeValue(USER_LOGIN_REQUEST)))
                .andExpect(status().isOk());
        assertTrue(validateJwtToken(getContent(resultActions.andReturn()), jwtSecret));
    }

    @Test
    public void loginUserNotFound() throws Exception {
        mockMvc.perform(post(URL + "signin")
                .contentType(APPLICATION_JSON)
                .content(writeValue(WRONG_USER_LOGIN_REQUEST)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void registerUser() throws Exception {
        RegisterRequest signupRequest = getNewRegisterRequest();
        mockMvc.perform(post(URL + "signup")
                        .contentType(APPLICATION_JSON)
                        .content(writeValue(signupRequest)))
                .andExpect(status().isOk());

        LoginRequest loginRequest = new LoginRequest(signupRequest.getPassword(), signupRequest.getEmail());
        String token = obtainToken(loginRequest);
        assertTrue(validateJwtToken(token, jwtSecret));
    }

    @Test
    public void registerUserPasswordsMismatch() throws Exception {
        RegisterRequest signupRequest = getNewRegisterRequest();
        signupRequest.setPasswordConfirm("wrong");
        mockMvc.perform(post(URL + "signup")
                .contentType(APPLICATION_JSON)
                .content(writeValue(signupRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerUserExist() throws Exception {
        RegisterRequest signupRequest = getNewRegisterRequest();
        signupRequest.setEmail(TEST_MAIL);
        mockMvc.perform(post(URL + "signup")
                .contentType(APPLICATION_JSON)
                .content(writeValue(signupRequest)))
                .andExpect(status().isConflict());
    }

}