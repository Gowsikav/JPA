package com.xworkz.onlineexam.service;

import com.xworkz.onlineexam.dto.OnlineExamDTO;
import com.xworkz.onlineexam.entity.OnlineExamEntity;

import java.util.List;

public interface OnlineExamService {

    boolean save(OnlineExamDTO examDTO);
    List<OnlineExamDTO> findAllEntity();
    OnlineExamDTO findById(Integer id);
    String updateOnlineExamById(OnlineExamDTO dto);
    String deleteById(Integer id);
    List<OnlineExamDTO> searchBySubject(String name);
}
