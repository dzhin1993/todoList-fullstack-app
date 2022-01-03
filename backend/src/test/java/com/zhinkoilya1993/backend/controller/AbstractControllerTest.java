package com.zhinkoilya1993.backend.controller;

import com.zhinkoilya1993.backend.service.TodoService;
import com.zhinkoilya1993.backend.to.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import static com.zhinkoilya1993.backend.util.JsonUtil.*;
import static com.zhinkoilya1993.backend.util.TestUtil.getContent;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebAppConfiguration
@SpringBootTest
public class AbstractControllerTest {

    @Value("${jwtSecret}")
    protected String jwtSecret;

    @Autowired
    protected TodoService todoService;

    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    @PostConstruct
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .apply(springSecurity())
                .build();
    }

    protected String obtainToken(LoginRequest loginRequest) throws Exception {
        ResultActions result = mockMvc.perform(post("/api/auth/signin")
                .contentType(APPLICATION_JSON)
                .content(writeValue(loginRequest)));
        return getContent(result.andReturn());
    }

    protected ResultActions perform(RequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }

    protected ResultActions performWithAuth(MockHttpServletRequestBuilder builder, LoginRequest request) throws Exception {
        String token = obtainToken(request);
        builder.header("Authorization", "Bearer " + token);
        return mockMvc.perform(builder);
    }
}
