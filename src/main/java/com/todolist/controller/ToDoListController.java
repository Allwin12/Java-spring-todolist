package com.todolist.controller;

import com.todolist.entity.ToDoList;
import com.todolist.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class ToDoListController {
    @Autowired
    public ToDoListService service;

    // Add a new task
    @PostMapping("/task")
    public ToDoList addTask(@RequestBody ToDoList task) {
        return service.addToDoList(task);
    }

    // List all tasks
    @GetMapping("/task")
    public Page<ToDoList> getTaskList(@RequestParam(required = false) Boolean completed,
                                      @RequestParam(required = false, defaultValue = "0") Integer page,
                                      @RequestParam(required = false, defaultValue = "10") Integer size) {
        return service.getToDoList(completed, page, size);
    }

    @PutMapping("/task/{id}")
    public ToDoList completeTask(@PathVariable Integer id) {
        return service.completeTask(id);
    }

    /*
    Return data using a Hashmap
     */
    @GetMapping("/numbers/{range}")
    public HashMap<String, ?> getNumbers(@PathVariable Integer range) {
        HashMap<String, List<Integer>> response = new HashMap<>();
        List<Integer> evenNumbers = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();
        for (int i = 1; i <= range; i++) {
            if (i % 2 == 0) {
                evenNumbers.add(i);
            } else {
                oddNumbers.add(i);
            }
        }
        response.put("even", evenNumbers);
        response.put("odd", oddNumbers);
        return response;
    }

    public class CustomResponse {
        HashMap<String, CustomObject> response = new HashMap<>();
//        CustomObject r = new CustomObject(evenNumbers, oddNumbers, data);
//        response.put("data", r);
    }

    public static class CustomObject {
        private final List<Integer> evenNumbers;
        private final List<Integer> oddNumbers;
        private final String message;
        private final String requestedAt;
        private final Page<ToDoList> toDoList;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        public CustomObject(List<Integer> evenNumbers, List<Integer> oddNumbers, Page<ToDoList> toDoList) {
            this.evenNumbers = evenNumbers;
            this.oddNumbers = oddNumbers;
            this.message = "This is a sample text";
            this.requestedAt = simpleDateFormat.format(date);
            this.toDoList = toDoList;
        }

        public String getMessage() {
            return message;
        }

        public List<Integer> getEvenNumbers() {
            return evenNumbers;
        }

        public List<Integer> getOddNumbers() {
            return oddNumbers;
        }

        public String getRequestedAt() {
            return requestedAt;
        }

        public Page<ToDoList> getToDoList() {
            return toDoList;
        }
    }
}
