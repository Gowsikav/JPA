package com.xworkz.task.runner;

import com.xworkz.task.entity.TaskEntity;
import com.xworkz.task.service.TaskService;
import com.xworkz.task.service.TaskServiceImpl;
import com.xworkz.task.util.DBConnection;
import com.xworkz.task.util.Priority;
import com.xworkz.task.util.Status;

import java.time.LocalDate;
import java.util.Optional;

public class TaskRunner {
    public static void main(String[] args) {

        TaskService taskService=new TaskServiceImpl();
        TaskEntity taskEntity=new TaskEntity();
        taskEntity.setTitle("Optimize database queries");
        taskEntity.setStatus(Status.PENDING);
        taskEntity.setDescription("Analyze and optimize slow-running SQL queries for report generation");
        taskEntity.setPriority(Priority.HIGH);
        taskEntity.setCreatedAt(LocalDate.of(2022,1,10));
        taskEntity.setDueDate(LocalDate.of(2022,1,15));

        if(taskService.save(taskEntity))
        {
            System.out.println("Data saved");
        }else {
            System.out.println("Data is not saved");
        }

        Integer id=3;
        if(taskService.delete(id))
        {
            System.out.println("Id "+id+" is deleted");
        }else {
            System.out.println("id "+id+" is not deleted");
        }

        Integer updateId=6;
        if(taskService.UpdateById(updateId,Status.IN_PROGRESS))
        {
            System.out.println("Data is updated");
        }else {
            System.out.println("Data is not updated");
        }

        String title="Write unit tests for service layer";
        Optional<TaskEntity> optional=taskService.findByTitle(title);
        if(optional.isPresent())
        {
            System.out.println("Data is found");
            System.out.println(optional.get());
        }else {
            System.out.println("Data is not found");
        }

        Optional<TaskEntity> optionalTaskEntity=taskService.findByPriority(Priority.LOW);
        if(optionalTaskEntity.isPresent())
        {
            System.out.println("Data is found");
            System.out.println(optionalTaskEntity.get());
        }else {
            System.out.println("Data is not found");
        }

        LocalDate dueDate=LocalDate.of(2023,9,10);
        Optional<TaskEntity> optionalTask=taskService.findByDueDate(dueDate);
        if(optionalTask.isPresent())
        {
            System.out.println("Data is found");
            System.out.println(optionalTask.get());
        }else {
            System.out.println("Data is not found");
        }

        DBConnection.closeResource();

    }
}
