package com.xworkz.onlineexam.repository;

import com.xworkz.onlineexam.entity.OnlineExamEntity;

import java.util.List;

public interface OnlineExamRepository {

    boolean save(OnlineExamEntity entity);
    List<OnlineExamEntity> findAllEntity();
}
