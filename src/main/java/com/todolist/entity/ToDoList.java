package com.todolist.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ToDoList {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String task;
    private Boolean completed = false;
    @CreationTimestamp
    private Date createdAt;
    private Date completedAt;

    public void setTask(String task) {
        this.task = task;
    }

    public void setCompletedAt(Date date) {
        this.completedAt = date;
    }

    public void setCompleted(Boolean bool) {
        this.completed = true;
    }

    public Integer getId() {
        return this.id;
    }

    public String getTask() {
        return this.task;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Date getCompletedAt() {
        return this.completedAt;
    }

    public Boolean getCompleted() {
        return this.completed;
    }
}

