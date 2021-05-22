package com.todolist.repository;

import com.todolist.entity.ToDoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {
    @Query("select task from ToDoList task where task.completed=true ")
    public Page<ToDoList> getCompletedTasks(Pageable pageable);

    @Query("select task from ToDoList task where task.completed=false")
    public Page<ToDoList> getIncompleteTasks(Pageable pageable);
}
