package com.zhinkoilya1993.backend.controller;

import com.zhinkoilya1993.backend.model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.zhinkoilya1993.backend.controller.TodoItemController.URL;
import static com.zhinkoilya1993.backend.TestData.*;
import static com.zhinkoilya1993.backend.util.JsonUtil.writeValue;
import static com.zhinkoilya1993.backend.util.TestUtil.readFromJson;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TodoItemControllerTest extends AbstractControllerTest {

    @Test
    void getAll() throws Exception {
        performWithAuth(MockMvcRequestBuilders.get(URL)
                .contentType(APPLICATION_JSON), USER_LOGIN_REQUEST)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TASK_MATCHER.contentJson(TASKS))
                .andExpect(status().isOk());
    }

    @Test
    void getAllByCompleted() throws Exception {
        performWithAuth(MockMvcRequestBuilders.get("/api/todoList/by-completed")
                .param("completed", FALSE.toString())
                .contentType(APPLICATION_JSON), USER_LOGIN_REQUEST)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TASK_MATCHER.contentJson(UN_COMPLETED))
                .andExpect(status().isOk());
    }

    @Test
    void get() throws Exception {
        performWithAuth(MockMvcRequestBuilders.get("/api/todoList/" + TASK_ID)
                .contentType(APPLICATION_JSON), USER_LOGIN_REQUEST)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TASK_MATCHER.contentJson(TASK_1))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        performWithAuth(MockMvcRequestBuilders
                .delete("/api/todoList/" + TASK_FOR_DELETE_ID), USER_2_LOGIN_REQUEST)
                .andExpect(status().isNoContent());
        assertNull(todoService.get(TASK_FOR_DELETE_ID, USER_2_ID));
    }

    @Test
    void update() throws Exception {
        var taskForUpdate = getUpdated();
        taskForUpdate.setName("updated");
        ResultActions actions = performWithAuth(MockMvcRequestBuilders
                .post("/api/todoList/")
                .contentType(APPLICATION_JSON)
                .content(writeValue(taskForUpdate)), USER_2_LOGIN_REQUEST)
                .andExpect(status().isOk());

        Task updated = readFromJson(actions, Task.class);
        TASK_MATCHER.assertMatch(updated, taskForUpdate);
        TASK_MATCHER.assertMatch(todoService.get(USER_2_ID, taskForUpdate.getId()), taskForUpdate);
    }

    @Test
    void createWithLocation() throws Exception {
        var taskForCreate = getTaskNew();
        ResultActions actions = performWithAuth(MockMvcRequestBuilders
                .post("/api/todoList/")
                .contentType(APPLICATION_JSON)
                .content(writeValue(taskForCreate)), USER_LOGIN_REQUEST)
                .andExpect(status().isOk());

        Task created = readFromJson(actions, Task.class);
        Integer createdId = created.getId();
        taskForCreate.setId(createdId);
        TASK_MATCHER.assertMatch(created, taskForCreate);
        TASK_MATCHER.assertMatch(todoService.get(USER_ID, createdId), taskForCreate);
    }

    @Test
    void complete() throws Exception {
        performWithAuth(MockMvcRequestBuilders
                .put("/api/todoList/complete")
                .param("id", String.valueOf(TASK_FOR_COMPLETED_ID))
                .param("completed", TRUE.toString()), USER_2_LOGIN_REQUEST)
                .andExpect(status().isOk());

        Task completed = todoService.get(USER_2_ID,TASK_FOR_COMPLETED_ID);
        Assertions.assertTrue(completed.isCompleted());
    }
}