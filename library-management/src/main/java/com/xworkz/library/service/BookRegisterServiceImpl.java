package com.xworkz.library.service;

import com.xworkz.library.dto.BookRegisterDTO;
import com.xworkz.library.entity.BookRegisterEntity;
import com.xworkz.library.repository.BookRegisterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookRegisterServiceImpl implements BookRegisterService{

    @Autowired
    private BookRegisterRepository repository;

    public BookRegisterServiceImpl()
    {
        System.out.println("BookRegisterService implementation constructor");
    }

    @Override
    public boolean save(BookRegisterDTO dto) {
        System.out.println("save method in service");
        BookRegisterEntity entity=new BookRegisterEntity();
        BeanUtils.copyProperties(dto,entity);
        return repository.save(entity);
    }

    @Override
    public List<BookRegisterDTO> getAll() {
        System.out.println("getAll method in service");
        List<BookRegisterEntity> entities=repository.getAll();
        List<BookRegisterDTO> dtos=new ArrayList<>();
        for(BookRegisterEntity entity:entities)
        {
            BookRegisterDTO dto=new BookRegisterDTO();
            BeanUtils.copyProperties(entity,dto);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public BookRegisterDTO getSingleDataById(Integer id) {
        System.out.println("getSingleDataById method in service");
        BookRegisterEntity entity=repository.getSingleDataById(id);
        BookRegisterDTO dto=new BookRegisterDTO();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }

    @Override
    public boolean updateDataById(BookRegisterDTO dto) {
        System.out.println("updateDataById method in service");
        BookRegisterEntity entity=new BookRegisterEntity();
        BeanUtils.copyProperties(dto,entity);
        return repository.updateDataById(entity);
    }

    @Override
    public boolean deleteDataById(Integer id) {
        System.out.println("deleteDataById method in service");
        return repository.deleteDataById(id);
    }
}
