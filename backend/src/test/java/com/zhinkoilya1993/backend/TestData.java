package com.zhinkoilya1993.backend;

import com.zhinkoilya1993.backend.model.Task;
import com.zhinkoilya1993.backend.model.User;
import com.zhinkoilya1993.backend.to.LoginRequest;
import com.zhinkoilya1993.backend.to.RegisterRequest;

import java.util.List;

import static java.time.LocalDateTime.of;
import static java.time.Month.JANUARY;

public class TestData {

    public static final TestMatcher<Task> TASK_MATCHER = TestMatcher.fieldsComparorOf(Task.class, "user");

    public static final Integer USER_ID = 1;
    public static final Integer USER_2_ID = USER_ID + 1;

    public static final User USER = new User(USER_ID, "password", "test@mail.ru");
    public static final User USER_2 = new User(USER_2_ID, "password2", "test2@mail.ru");

    public static final String TEST_MAIL = "test@mail.ru";
    public static final LoginRequest USER_LOGIN_REQUEST = new LoginRequest("password", TEST_MAIL);
    public static final LoginRequest USER_2_LOGIN_REQUEST = new LoginRequest("password2", "test2@mail.ru");
    public static final LoginRequest WRONG_USER_LOGIN_REQUEST = new LoginRequest("wrong", "wrong@mail.ru");

    public static final Integer TASK_ID = 1;
    public static final Integer TASK_FOR_DELETE_ID = TASK_ID + 3;
    public static final Integer TASK_FOR_COMPLETED_ID = TASK_ID + 5;

    public static final Task TASK_1 = new Task(TASK_ID, "todo1", of(2021, JANUARY, 30, 10, 0), false, USER);
    public static final Task TASK_2 = new Task(TASK_ID + 1, "todo2", of(2021, JANUARY, 30, 11, 0), false, USER);
    public static final Task TASK_3 = new Task(TASK_ID + 2, "todo3", of(2021, JANUARY, 30, 12, 0), true, USER);

    public static final List<Task> TASKS = List.of(TASK_1, TASK_2, TASK_3);

    public static final List<Task> UN_COMPLETED = List.of(TASK_1, TASK_2);

    public static Task getTaskNew() {
        return new Task("created", of(2021, JANUARY, 31, 10, 0), false);
    }

    public static Task getUpdated() {
        return new Task(TASK_ID + 4, "todo5", of(2021, JANUARY, 30, 14, 0), true, USER_2);
    }

    public static RegisterRequest getNewRegisterRequest() {
        return new RegisterRequest("created", "created", "created@mail.ru");
    }
}
