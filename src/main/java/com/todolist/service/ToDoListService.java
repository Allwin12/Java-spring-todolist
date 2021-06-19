package com.todolist.service;

import com.todolist.entity.ToDoList;
import com.todolist.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
public class ToDoListService {
    @Autowired
    public ToDoListRepository repository;

    public static class Response {
        private final int total;
        private final List<?> data;
        private final int page;
        private final int size;

        public Response(List<?> result, Integer total, int page, int size) {
            this.total = total;
            this.data = result;
            this.page = page;
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public List<?> getData() {
            return data;
        }

        public int getPage() {
            return page;
        }

        public int getSize() {
            return size;
        }
    }

    public ToDoList addToDoList(ToDoList task) {
        return repository.save(task);
    }

    public Page<ToDoList> getToDoList(Boolean completed, Integer page, Integer size) {
        // Pagination object
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public Response filterTasks(Boolean completed, String from, String to, int page, int size, String search) {
        int fromIndex = (page - 1) * size;
        int total = 0;
        List<ToDoList> tasks = repository.findTasks(completed, from, to, search);
        List<ToDoList> paginated_data;
        if (tasks == null || tasks.size() <= fromIndex) {
            paginated_data = Collections.emptyList();
        } else {
            total = tasks.size();
            paginated_data = tasks.subList(fromIndex, Math.min(fromIndex + size, tasks.size()));
        }
        return new Response(paginated_data, total, page, size);
    }

    public ToDoList completeTask(Integer id) {
        ToDoList existing_task = repository.findById(id).orElse(null);
        assert existing_task != null;
        Date currentTime = new Date();
        existing_task.setCompletedAt(currentTime);
        existing_task.setCompleted(true);
        return repository.save(existing_task);
    }

    public List<String> getAllTasksNames(){
        return repository.getAllTaskNames();
    }

    public List<ToDoListRepository.TaskIdAndName> getTaskIdAndName(){
        return repository.getTaskIdAndName();
    }

    public Boolean deleteTaskById(Integer id){
        ToDoList existing_task = repository.findById(id).orElse(null);
        if (existing_task == null){
            return false;
        }
        else {
            repository.deleteById(id);
            return true;
        }
    }

    public ToDoList getTaskById(Integer id){
        return repository.findById(id).orElse(null);
    }
}

