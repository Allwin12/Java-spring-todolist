package com.todolist.repository;

import com.todolist.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Integer>, ToDoListRepositoryCustom {

    @Query("select task.task from ToDoList task order by task.task asc ")
    public List<String> getAllTaskNames();

    interface TaskIdAndName{
        Integer getId();
        String getTask();
        default String getMessage()
        {
            return "This is a custom message";
        }
    }

    @Query("select task from ToDoList task")
    public List<TaskIdAndName> getTaskIdAndName();

}
