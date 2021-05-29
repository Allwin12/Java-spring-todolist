package com.todolist.repository;

import com.todolist.entity.ToDoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {
    @Query("select task from ToDoList task where task.completed=true ")
    public Page<ToDoList> getCompletedTasks(Pageable pageable, List<ToDoList> tasks);

    @Query("select task from ToDoList task where task.completed=false")
    public Page<ToDoList> getIncompleteTasks(Pageable pageable);

    //completed tasks in a range
    public Page<ToDoList> findAllByCompletedAtAfter(Pageable page, Date from, Date to);

}
