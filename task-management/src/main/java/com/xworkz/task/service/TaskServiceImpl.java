package com.xworkz.task.service;

import com.xworkz.task.entity.TaskEntity;
import com.xworkz.task.repository.TaskRepository;
import com.xworkz.task.repository.TaskRepositoryImpl;
import com.xworkz.task.util.Priority;
import com.xworkz.task.util.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository=new TaskRepositoryImpl();

    public TaskServiceImpl()
    {
        System.out.println("Task Service Implementation constructor");
    }

    private boolean validateDetails(TaskEntity taskEntity)
    {
        System.out.println("validateDetails method in service");
        if(taskEntity!=null)
        {
            System.out.println("task entity is valid");
        }else {
            System.out.println("task entity is not valid");
            return false;
        }

        String title= taskEntity.getTitle();
        if(title!=null && title.length()>3 && title.length()<50)
        {
            System.out.println("title is valid");
        }else {
            System.out.println("title is not valid");
            return false;
        }

        String description=taskEntity.getDescription();
        if(description!=null && description.length()>5 && description.length()<100)
        {
            System.out.println("description is valid");
        }else {
            System.out.println("description is not valid");
            return false;
        }

        LocalDate createdAt=taskEntity.getCreatedAt();
        if(createdAt!=null && createdAt.isBefore(LocalDate.now()))
        {
            System.out.println("created at is valid");
        }else {
            System.out.println("created at is not valid");
            return false;
        }

        LocalDate dueDate=taskEntity.getDueDate();
        if(dueDate!=null && dueDate.isAfter(createdAt))
        {
            System.out.println("due date is valid");
        }else {
            System.out.println("due date is not valid");
            return false;
        }

        System.out.println("All details are valid");
        return true;
    }

    @Override
    public boolean save(TaskEntity taskEntity) {
        System.out.println("save method in service");
        if(validateDetails(taskEntity))
        {
            return taskRepository.save(taskEntity);
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        System.out.println("delete method in service");
        if(id!=null && id >0)
        {
            System.out.println("id is valid");
           return taskRepository.delete(id);
        }else {
            System.out.println("id not valid");
        }
        return false;
    }

    @Override
    public boolean UpdateById(Integer id, Status status) {
        System.out.println("update by id method in service");
        if(id!=null && id>0)
        {
            System.out.println("id is valid");
            if(status.equals(Status.COMPLETED) || status.equals(Status.IN_PROGRESS) || status.equals(Status.PENDING))
            {
                System.out.println("status is valid");
                return taskRepository.UpdateById(id,status);
            }else {
                System.out.println("status is not valid");
                return false;
            }
        }else {
            System.out.println("id not valid");
        }
        return false;
    }

    @Override
    public Optional<TaskEntity> findByTitle(String taskTitle) {
        System.out.println("find by title method in service");
        if(taskTitle!=null && taskTitle.length()>3 && taskTitle.length()<50)
        {
            System.out.println("title is valid");
            Optional<TaskEntity> optionalTaskEntity= taskRepository.findByTitle(taskTitle);
            optionalTaskEntity.ifPresent(this::validateDetails);
            return optionalTaskEntity;
        }else {
            System.out.println("title is not valid");
        }
        return Optional.empty();
    }

    @Override
    public List<TaskEntity> findByPriority(Priority priority) {
        System.out.println("find by priority method in service");
        if(priority!=null )
        {
            if(priority.equals(Priority.HIGH) || priority.equals(Priority.MEDIUM)|| priority.equals(Priority.LOW))
            {
                System.out.println("priority is valid");
                return taskRepository.findByPriority(priority);
            }else {
                System.out.println("priority is not valid");
            }
        }
        return null;
    }

    @Override
    public Optional<TaskEntity> findByDueDate(LocalDate taskDueDate) {
        System.out.println("find by dueDate method in service");
        if(taskDueDate!=null)
        {
            System.out.println("dueDate is valid");
            Optional<TaskEntity> optionalTaskEntity= taskRepository.findByDueDate(taskDueDate);
            optionalTaskEntity.ifPresent(this::validateDetails);
            return optionalTaskEntity;
        }else {
            System.out.println("due date is not valid");
        }
        return Optional.empty();
    }

    @Override
    public List<TaskEntity> findAll() {
        System.out.println("findAll method in service");
        return taskRepository.findAll();
    }


    @Override
    public TaskEntity findById(Integer id) {
        System.out.println("find by id i service");
        if(id !=null && id>0)
        {
            System.out.println("id is valid");
            return taskRepository.findById(id);
        }else {
            System.out.println("id is not valid");
        }
        return null;
    }

    @Override
    public TaskEntity updateStatusByTitle(Integer id, Status status, String title) {
        System.out.println("updateStatusByTitle method in service");
        return  taskRepository.updateStatusByTitle(id,status,title);

    }

    @Override
    public TaskEntity updatePriorityByDueDate(Integer id, Priority priority, LocalDate dueDate) {
        System.out.println("updatePriorityByDueDate method in service");
        return  taskRepository.updatePriorityByDueDate(id,priority,dueDate);
    }

    @Override
    public TaskEntity updateDueDateByStatus(Integer id, Status status, LocalDate dueDate) {
        System.out.println("updateDueDateByStatus method in service");
        return  taskRepository.updateDueDateByStatus(id,status,dueDate);
    }

    @Override
    public List<String> getTitle() {
        System.out.println("getTitle method in servie");
        return taskRepository.getTitle();
    }

    @Override
    public List<LocalDate> getDueDate() {
        System.out.println("getDueDate method in servie");
        return taskRepository.getDueDate();
    }

    @Override
    public List<Object[]> getStatusAndCreatedAt() {
        System.out.println("getStatusAndCreatedAt method in servie");
        return taskRepository.getStatusAndCreatedAt();
    }

}
