package com.xworkz.movie.service;

import com.xworkz.movie.entity.MovieEntity;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    boolean save(MovieEntity movieEntity);
    boolean deleteById(Integer id);
    boolean updateById(Integer id,Float ratings);
    Optional<MovieEntity> findByMovieName(String movieName);
    Optional<MovieEntity> findByDirectorName(String directorName);
    List<MovieEntity> findByHeroName(String heroName);
    List<MovieEntity> findAll();
    MovieEntity findById(Integer id);
}
