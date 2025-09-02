package com.xworkz.library.repository;

import com.xworkz.library.entity.BookRegisterEntity;

import java.util.List;

public interface BookRegisterRepository {

    boolean save(BookRegisterEntity entity);
    List<BookRegisterEntity> getAll();
    BookRegisterEntity getSingleDataById(Integer id);
    boolean updateDataById(BookRegisterEntity entity);
    boolean deleteDataById(Integer id);
}
