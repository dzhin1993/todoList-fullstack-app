package com.zhinkoilya1993.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task extends AbstractNamedEntity {

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private boolean completed = false;

    @JsonIgnore
    //@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Task(Task task) {
        this(task.getId(), task.getName(), task.getDateTime(), task.isCompleted(), task.getUser());
    }

    public Task(String name, LocalDateTime dateTime, boolean completed) {
        this(null, name, dateTime, completed, null);
    }

    public Task(Integer id, String name, LocalDateTime dateTime, boolean completed, User user) {
        this.setId(id);
        this.setName(name);
        this.dateTime = dateTime;
        this.completed = completed;
        this.user = user;
    }
}