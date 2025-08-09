package com.xworkz.onlineexam.service;

import com.xworkz.onlineexam.dto.OnlineExamDTO;
import com.xworkz.onlineexam.entity.OnlineExamEntity;
import com.xworkz.onlineexam.repository.OnlineExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineExamServiceImpl implements OnlineExamService{

    @Autowired
    private OnlineExamRepository onlineExamRepository;

    public OnlineExamServiceImpl()
    {
        System.out.println("OnlineExamServiceImpl constructor");
    }

    @Override
    public boolean save(OnlineExamDTO examDTO) {
        System.out.println("save method in service");
        System.out.println("service data: "+examDTO);

        OnlineExamEntity onlineExamEntity=new OnlineExamEntity();
        onlineExamEntity.setSubject(examDTO.getSubject());
        onlineExamEntity.setTotalMarks(examDTO.getTotalMarks());
        onlineExamEntity.setExamDate(examDTO.getExamDate());
        onlineExamEntity.setDurationHours(examDTO.getDurationHours());
        onlineExamEntity.setNoOfQuestions(examDTO.getNoOfQuestions());
        onlineExamEntity.setDurationMinutes(examDTO.getDurationMinutes());


        return onlineExamRepository.save(onlineExamEntity);
    }
}
