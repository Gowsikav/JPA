package com.xworkz.onlineexam.repository;

import com.xworkz.onlineexam.entity.OnlineExamEntity;

import java.util.List;

public interface OnlineExamRepository {

    boolean save(OnlineExamEntity entity);
    List<OnlineExamEntity> findAllEntity();
    OnlineExamEntity findById(Integer id);
    Boolean updateOnlineExamById(OnlineExamEntity entity);
    Boolean deleteById(Integer id);
    List<OnlineExamEntity> searchBySubject(String name);
}
