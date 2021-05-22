package com.todolist.controller;

import com.todolist.entity.ToDoList;
import com.todolist.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ToDoListController {
    @Autowired
    public ToDoListService service;

    // Add a new task
    @PostMapping("/task")
    public ToDoList addTask(@RequestBody ToDoList task){
        return service.addToDoList(task);
    }

    // List all tasks
    @GetMapping("/task")
    public Page<ToDoList> getTaskList(@RequestParam(required = false) Boolean completed,
                                      @RequestParam(required = false, defaultValue = "0") Integer page,
                                      @RequestParam(required = false, defaultValue = "10") Integer size){
        return service.getToDoList(completed, page, size);
    }

    @PutMapping("/task/{id}")
    public ToDoList completeTask(@PathVariable Integer id){
        return service.completeTask(id);
    }

    public void BuildResponse(){

    }
}
