package com.todolist.controller;

import com.todolist.entity.ToDoList;
import com.todolist.repository.ToDoListRepository;
import com.todolist.service.ToDoListService;
import com.todolist.service.ToDoListService.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class ToDoListController {
    @Autowired
    public ToDoListService service;

    //Fetch task ID and Name
    @GetMapping("/task-id-name")
    public List<ToDoListRepository.TaskIdAndName> getTaskIdAndName(){
        return service.getTaskIdAndName();
    }

    // Fetch a list of task names
    @GetMapping("/task-names")
    public List<String> getAllTasksName() {
        return service.getAllTasksNames();
    }

    // Add a new task
    @PostMapping("/task")
    public ToDoList addTask(@RequestBody ToDoList task) {
        return service.addToDoList(task);
    }


    // Update a task - Mark as completed
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

    // List all tasks with custom filters and pagination
    @GetMapping("/task")
    public Response getAllTasks(@RequestParam(required = false) Boolean completed,
                                @RequestParam(required = false) String from,
                                @RequestParam(required = false) String to,
                                @RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "10") Integer size,
                                @RequestParam(required = false) String search) {
        return service.filterTasks(completed, from, to, page, size, search);
    }

    // Delete a task by ID
    @DeleteMapping("/task/{id}")
    public ResponseEntity<HashMap<String, ?>> deleteTaskById(@PathVariable Integer id){
        HashMap<String, Object> response = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();

        headers.add("foo", "bar");
        Boolean deleted = service.deleteTaskById(id);
        if(deleted){
            response.put("status", "Success");
            response.put("message", "Task deleted successfully!");
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
        }
        else {
            response.put("status", "Failure");
            response.put("Message", "Task not found");
            response.put("code", 1001);
            return ResponseEntity.status(400).headers(headers).body(response);
        }
    }

    @GetMapping("/task/{id}")
    public ToDoList findTaskById(@PathVariable Integer id){
        return service.getTaskById(id);
    }
}
