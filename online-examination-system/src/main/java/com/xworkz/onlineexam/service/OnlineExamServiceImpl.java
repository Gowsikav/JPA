package com.xworkz.onlineexam.service;

import com.xworkz.onlineexam.dto.OnlineExamDTO;
import com.xworkz.onlineexam.entity.OnlineExamEntity;
import com.xworkz.onlineexam.repository.OnlineExamRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OnlineExamServiceImpl implements OnlineExamService {

    @Autowired
    private OnlineExamRepository onlineExamRepository;

    public OnlineExamServiceImpl() {
        System.out.println("OnlineExamServiceImpl constructor");
    }

    @Override
    public boolean save(OnlineExamDTO examDTO) {
        System.out.println("save method in service");
        System.out.println("service data: " + examDTO);

        OnlineExamEntity onlineExamEntity = new OnlineExamEntity();
        onlineExamEntity.setSubject(examDTO.getSubject());
        onlineExamEntity.setTotalMarks(examDTO.getTotalMarks());
        onlineExamEntity.setExamDate(examDTO.getExamDate());
        onlineExamEntity.setDurationHours(examDTO.getDurationHours());
        onlineExamEntity.setNoOfQuestions(examDTO.getNoOfQuestions());
        onlineExamEntity.setDurationMinutes(examDTO.getDurationMinutes());


        return onlineExamRepository.save(onlineExamEntity);
    }

    @Override
    public List<OnlineExamDTO> findAllEntity() {
        System.out.println("findAllEntity method in service");
        List<OnlineExamDTO> onlineExamDTOS = null;
        List<OnlineExamEntity> examEntities = onlineExamRepository.findAllEntity();
        onlineExamDTOS = examEntities.stream().map(entity -> {
            OnlineExamDTO examDTO = new OnlineExamDTO();
            examDTO.setExamId(entity.getExamId());
            examDTO.setExamDate(entity.getExamDate());
            examDTO.setSubject(entity.getSubject());
            examDTO.setDurationMinutes(entity.getDurationMinutes());
            examDTO.setDurationHours(entity.getDurationHours());
            examDTO.setTotalMarks(entity.getTotalMarks());
            examDTO.setNoOfQuestions(entity.getNoOfQuestions());
            return examDTO;
        }).collect(Collectors.toList());
        return onlineExamDTOS;
    }

    @Override
    public OnlineExamDTO findById(Integer id) {
        System.out.println("findById method in service");
        OnlineExamDTO examDTO = new OnlineExamDTO();
        OnlineExamEntity entity = onlineExamRepository.findById(id);
        examDTO.setExamId(entity.getExamId());
        examDTO.setExamDate(entity.getExamDate());
        examDTO.setSubject(entity.getSubject());
        examDTO.setDurationMinutes(entity.getDurationMinutes());
        examDTO.setDurationHours(entity.getDurationHours());
        examDTO.setTotalMarks(entity.getTotalMarks());
        examDTO.setNoOfQuestions(entity.getNoOfQuestions());
        return examDTO;
    }

    @Override
    public String updateOnlineExamById(OnlineExamDTO dto) {
        System.out.println("updateOnlineExamById method in service");
        OnlineExamEntity entity=new OnlineExamEntity();
        BeanUtils.copyProperties(dto,entity);
        if(onlineExamRepository.updateOnlineExamById(entity))
            return "Data updated";
        return "Data not updated";
    }

    @Override
    public String deleteById(Integer id) {
        System.out.println("deleteById method in service");
        if(onlineExamRepository.deleteById(id))
            return "Data Deleted";
        return "Data not deleted";
    }

    @Override
    public List<OnlineExamDTO> searchBySubject(String name) {
        System.out.println("searchBySubject method in service");
        List<OnlineExamDTO> list=new ArrayList<>();
        List<OnlineExamEntity> examEntities=onlineExamRepository.searchBySubject(name);
        for(OnlineExamEntity entity:examEntities)
        {
            OnlineExamDTO examDTO=new OnlineExamDTO();
            BeanUtils.copyProperties(entity,examDTO);
            list.add(examDTO);
        }
        return list;
    }
}
