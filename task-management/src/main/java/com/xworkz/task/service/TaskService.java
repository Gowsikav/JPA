package com.xworkz.task.service;

import com.xworkz.task.entity.TaskEntity;
import com.xworkz.task.util.Priority;
import com.xworkz.task.util.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    boolean save(TaskEntity taskEntity);
    boolean delete(Integer id);
    boolean UpdateById(Integer id, Status status );
    Optional<TaskEntity> findByTitle(String taskTitle);
    Optional<TaskEntity> findByDueDate(LocalDate taskDueDate);
    List<TaskEntity> findByPriority(Priority priority);
    TaskEntity findById(Integer id);
    List<TaskEntity> findAll();
    TaskEntity updateStatusByTitle(Integer id,Status status,String title);
    TaskEntity updatePriorityByDueDate(Integer id,Priority priority,LocalDate dueDate);
    TaskEntity updateDueDateByStatus(Integer id,Status status,LocalDate dueDate);

}
