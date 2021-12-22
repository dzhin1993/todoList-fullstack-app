package com.zhinkoilya1993.backend.repository;

import com.zhinkoilya1993.backend.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Task, Integer> {

    @Query("SELECT t FROM Task t WHERE t.user.id=?1 AND t.completed=?2")
    List<Task> getAllByCompletedAndUserId(int id, boolean completed);

    @Query("SELECT t FROM Task t WHERE t.user.id=?1")
    List<Task> getAllByUserId(int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Task t WHERE t.id=?1 AND t.user.id=?2")
    void deleteByUserId(int id, int userId);
}
