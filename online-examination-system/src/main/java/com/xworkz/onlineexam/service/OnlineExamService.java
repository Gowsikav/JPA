package com.xworkz.onlineexam.service;

import com.xworkz.onlineexam.dto.OnlineExamDTO;

import java.util.List;

public interface OnlineExamService {

    boolean save(OnlineExamDTO examDTO);
    List<OnlineExamDTO> findAllEntity();
}
