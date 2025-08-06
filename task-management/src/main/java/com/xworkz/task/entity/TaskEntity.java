package com.xworkz.task.entity;

import com.xworkz.task.util.Priority;
import com.xworkz.task.util.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "task_info")
@NamedQuery(name = "getTitle",query = "select a from TaskEntity a where a.title=:title")
@NamedQuery(name = "getPriority",query = "select a from TaskEntity a where a.priority=:priority")
@NamedQuery(name = "getDueDate",query = "select a from TaskEntity a where a.dueDate=:dueDate")
@NamedQuery(name = "findAll",query = "select a from TaskEntity a")
@NamedQuery(name = "findById",query = "select a from TaskEntity a where a.taskId=:id")
@NamedQuery(name = "updateStatusByTitle",query = "update TaskEntity a set a.status=:status where a.taskId=:id and a.title=:title")
@NamedQuery(name = "updatePriorityByDueDate",query = "update TaskEntity a set a.priority=:priority where a.taskId=:id and a.dueDate=:dueDate")
@NamedQuery(name = "updateDueDateByStatus",query = "update TaskEntity a set a.dueDate=:dueDate where a.taskId=:id and a.status=:status")
@NamedQuery(name = "getAllTitle",query = "select a.title from TaskEntity a")
@NamedQuery(name = "getAllDueDate",query = "select a.dueDate from TaskEntity a")
@NamedQuery(name = "getAllStatusAndCreatedAt",query = "select a.status,a.createdAt from TaskEntity a")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "created_at")
    private LocalDate createdAt;
}
