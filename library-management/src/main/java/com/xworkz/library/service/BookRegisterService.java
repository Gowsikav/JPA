package com.xworkz.library.service;

import com.xworkz.library.dto.BookRegisterDTO;

import java.util.List;

public interface BookRegisterService {

    boolean save(BookRegisterDTO dto);
    List<BookRegisterDTO> getAll();
    BookRegisterDTO getSingleDataById(Integer id);
    boolean updateDataById(BookRegisterDTO dto);
    boolean deleteDataById(Integer id);
}
