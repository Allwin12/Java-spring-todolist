package com.todolist.repository;

import com.todolist.entity.ToDoList;

import java.util.List;

public interface ToDoListRepositoryCustom {
    List<ToDoList> findTasks(Boolean completed, String from, String to, String search);
}
