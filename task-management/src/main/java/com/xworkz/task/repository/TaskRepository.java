package com.xworkz.task.repository;

import com.xworkz.task.entity.TaskEntity;
import com.xworkz.task.util.Priority;
import com.xworkz.task.util.Status;

import java.time.LocalDate;
import java.util.Optional;

public interface TaskRepository {

    boolean save(TaskEntity taskEntity);
    boolean delete(Integer id);
    boolean UpdateById(Integer id, Status status );
    Optional<TaskEntity> findByTitle(String taskTitle);
    Optional<TaskEntity> findByPriority(Priority priority);
    Optional<TaskEntity> findByDueDate(LocalDate taskDueDate);

}
