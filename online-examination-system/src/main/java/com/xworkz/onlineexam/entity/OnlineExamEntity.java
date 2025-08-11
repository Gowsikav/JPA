package com.xworkz.onlineexam.entity;

import lombok.Data;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "online_exam_info")
@NamedQuery(name = "findAllEntity",query = "select a from OnlineExamEntity a")
public class OnlineExamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Integer examId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "no_of_questions")
    private Integer noOfQuestions;

    @Column(name = "exam_date")
    private LocalDate examDate;

    @Column(name = "total_marks")
    private Integer totalMarks;

    @Column(name = "duration_hours")
    private Integer durationHours;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

}
