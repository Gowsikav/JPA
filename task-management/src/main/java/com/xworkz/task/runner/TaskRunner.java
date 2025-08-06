package com.xworkz.task.runner;

import com.xworkz.task.entity.TaskEntity;
import com.xworkz.task.service.TaskService;
import com.xworkz.task.service.TaskServiceImpl;
import com.xworkz.task.util.DBConnection;
import com.xworkz.task.util.Priority;
import com.xworkz.task.util.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TaskRunner {
    public static void main(String[] args) {

        TaskService taskService = new TaskServiceImpl();
//        TaskEntity taskEntity=new TaskEntity();
//        taskEntity.setTitle("Optimize database queries");
//        taskEntity.setStatus(Status.PENDING);
//        taskEntity.setDescription("Analyze and optimize slow-running SQL queries for report generation");
//        taskEntity.setPriority(Priority.HIGH);
//        taskEntity.setCreatedAt(LocalDate.of(2022,1,10));
//        taskEntity.setDueDate(LocalDate.of(2022,1,15));
//
//        if(taskService.save(taskEntity))
//        {
//            System.out.println("Data saved");
//        }else {
//            System.out.println("Data is not saved");
//        }
//
//        Integer id=3;
//        if(taskService.delete(id))
//        {
//            System.out.println("Id "+id+" is deleted");
//        }else {
//            System.out.println("id "+id+" is not deleted");
//        }
//
//        Integer updateId=6;
//        if(taskService.UpdateById(updateId,Status.IN_PROGRESS))
//        {
//            System.out.println("Data is updated");
//        }else {
//            System.out.println("Data is not updated");
//        }
//
//        String title="Write unit tests for service layer";
//        Optional<TaskEntity> optional=taskService.findByTitle(title);
//        if(optional.isPresent())
//        {
//            System.out.println("Data is found");
//            System.out.println(optional.get());
//        }else {
//            System.out.println("Data is not found");
//        }
//
//        LocalDate dueDate=LocalDate.of(2023,9,10);
//        Optional<TaskEntity> optionalTask=taskService.findByDueDate(dueDate);
//        if(optionalTask.isPresent())
//        {
//            System.out.println("Data is found");
//            System.out.println(optionalTask.get());
//        }else {
//            System.out.println("Data is not found");
//        }
//
//        List<TaskEntity> list=taskService.findByPriority(Priority.HIGH);
//        if(!list.isEmpty())
//        {
//            System.out.println("Data is found");
//           list.forEach(System.out::println);
//        }else {
//            System.out.println("Data is not found");
//        }

//        Integer id=1;
//        TaskEntity taskEntity2=taskService.findById(id);
//        if(taskEntity2!=null)
//        {
//            System.out.println("Data found");
//            System.out.println(taskEntity2);
//        }else {
//            System.out.println("data not found");
//        }

//        List<TaskEntity> taskEntityList = taskService.findAll();
//        if (!taskEntityList.isEmpty()) {
//            System.out.println("Data is found");
//            taskEntityList.forEach(System.out::println);
//        } else {
//            System.out.println("Data is not found");
//        }
//
//        TaskEntity taskEntity2 = null;
//        taskEntity2 = taskService.updateStatusByTitle(5, Status.COMPLETED, "Write unit tests for service layer");
//        System.out.println(taskEntity2);
//        taskEntity2 = taskService.updateDueDateByStatus(4, Status.COMPLETED, LocalDate.of(2023, 7, 20));
//        System.out.println(taskEntity2);
//        taskEntity2 = taskService.updatePriorityByDueDate(2, Priority.LOW, LocalDate.of(2023, 9, 10));
//        System.out.println(taskEntity2);

        List<String> title=taskService.getTitle();
        System.out.println("title list");
        title.forEach(System.out::println);

        List<LocalDate> localDates=taskService.getDueDate();
        System.out.println("Due dates list");
        localDates.forEach(System.out::println);

        List<Object[]> objects=taskService.getStatusAndCreatedAt();
        System.out.println("Status and Created At");
        objects.stream().map(e->e[0]+" : "+e[1]).forEach(System.out::println);

        DBConnection.closeResource();

    }
}
