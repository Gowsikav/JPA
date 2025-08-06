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
    List<MovieEntity> findByGenre(String genre);
    MovieEntity updateByMovieName(String movieName,Integer id,String directorName,float ratings);
    MovieEntity updateHeroName(Integer id,String heroName);
    MovieEntity updateGenre(String movieName, String genre, Integer id);
    List<String> getAllMovieName();
    List<Float> getAllRatings();
    List<Object> getAllReleaseDate();
    List<Object[]> getMovieNameAndHeroName();
}
