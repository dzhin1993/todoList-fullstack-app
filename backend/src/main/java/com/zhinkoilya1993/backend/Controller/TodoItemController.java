package com.zhinkoilya1993.backend.Controller;

import com.zhinkoilya1993.backend.model.Task;
import com.zhinkoilya1993.backend.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.zhinkoilya1993.backend.SecurityUtil.getUserId;

@RestController
@RequestMapping(value = TodoItemController.URL)
public class TodoItemController {

    static final String URL = "/api/todoList/";

    private final TodoService service;

    public TodoItemController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getAll() {
        return service.getAll(getUserId());
    }

    @GetMapping("by-completed")
    public List<Task> getAllByCompleted(@RequestParam(value = "completed") Boolean completed) {
        return service.getAllByCompleted(getUserId(), completed);
    }

    @GetMapping(value = "{id}")
    public Task get(@PathVariable int id) {
        return service.get(getUserId(), id);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(getUserId(), id);
    }

    @PostMapping
    public Task saveOrUpdate(@RequestBody @Valid Task task) {
        return service.saveOrUpdate(getUserId(), task);
    }

    @PutMapping("complete")
    public void complete(@RequestParam Integer id,
                         @RequestParam(value = "completed") Boolean completed) {
        service.complete(getUserId(), id, completed);
    }
}
