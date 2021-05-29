package com.todolist.service;

import com.todolist.entity.ToDoList;
import com.todolist.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ToDoListService {
    @Autowired
    public ToDoListRepository repository;

    public ToDoList addToDoList(ToDoList task) {
        return repository.save(task);
    }

    public Page<ToDoList> getToDoList(Boolean completed, Integer page, Integer size) {
        // Pagination object
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAll(pageable);
    }

    public ToDoList completeTask(Integer id) {
        ToDoList existing_task = repository.findById(id).orElse(null);
        assert existing_task != null;
        Date currentTime = new Date();
        existing_task.setCompletedAt(currentTime);
        existing_task.setCompleted(true);
        return repository.save(existing_task);
    }
}
