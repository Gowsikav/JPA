package com.xworkz.movie.repository;

import com.xworkz.movie.entity.MovieEntity;

import java.util.Optional;

public interface MovieRepository {

    boolean save(MovieEntity movieEntity);
    boolean deleteById(Integer id);
    boolean updateById(Integer id,Float ratings);
    Optional<MovieEntity> findByMovieName(String movieName);
    Optional<MovieEntity> findByHeroName(String heroName);
    Optional<MovieEntity> findByDirectorName(String directorName);

}
