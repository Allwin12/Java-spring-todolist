package com.todolist.service;

import com.todolist.entity.ToDoList;
import com.todolist.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

    public List<ToDoList> getAllTasks(Boolean isCompleted){
        List<ToDoList> tasks = repository.findAll();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate fromDate = LocalDate.parse("2021-09-09");
        Date date = Date.from(fromDate.atStartOfDay(defaultZoneId).toInstant());
        // Create a stream on retrieved data
        Stream<ToDoList> tasksStream = tasks.stream();

        // Filter that stream based on user input
        if(isCompleted) {
            tasksStream = tasksStream.filter(ToDoList::getCompleted);
        }

        tasksStream = tasksStream.filter(task -> task.getCompletedAt() != null).filter(task ->
                task.getCompletedAt().after(date));

        List<ToDoList> filteredTaskList = tasksStream.collect(Collectors.toList());
        return filteredTaskList;
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
