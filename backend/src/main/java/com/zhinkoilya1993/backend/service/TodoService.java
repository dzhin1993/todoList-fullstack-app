package com.zhinkoilya1993.backend.service;

import com.zhinkoilya1993.backend.model.Task;
import com.zhinkoilya1993.backend.repository.TodoRepository;
import com.zhinkoilya1993.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public List<Task> getAll(int ownerId) {
        return todoRepository.getAllByUserId(ownerId);
    }

    public List<Task> getAllByCompleted(int ownerId, boolean completed) {
        return todoRepository.getAllByCompletedAndUserId(ownerId, completed);
    }

    public Task get(int ownerId, int todId) {
        return todoRepository.findById(todId)
                .filter(task -> task.getUser().getId() == ownerId)
                .orElse(null);
    }

    @Transactional
    public Task saveOrUpdate(int ownerId, Task task) {
        if (!task.isNew() && get(ownerId, task.getId()) == null) {
            return null;
        }
        task.setUser(userRepository.getOne(ownerId));
        return todoRepository.save(task);
    }

    @Transactional
    public void delete(int ownerId, int todoId) {
        todoRepository.deleteByUserId(todoId, ownerId);
    }

    @Transactional
    public void complete(int ownerId, int todoId, boolean completed) {
        Task task = get(ownerId, todoId);
        if (task != null) {
            task.setCompleted(completed);
        }
    }
}
