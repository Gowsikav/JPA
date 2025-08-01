package com.xworkz.movie.runner;

import com.xworkz.movie.entity.MovieEntity;
import com.xworkz.movie.service.MovieService;
import com.xworkz.movie.service.MovieServiceImpl;
import com.xworkz.movie.util.DBConnection;

import java.time.LocalDate;
import java.util.Optional;

public class MovieRunner {
    public static void main(String[] args) {
        MovieService movieService=new MovieServiceImpl();
        MovieEntity movieEntity=new MovieEntity();
        movieEntity.setMovieName("Bheem");
        movieEntity.setGenre("Thriller");
        movieEntity.setDirectorName("Deepak");
        movieEntity.setHeroName("Rajesh");
        movieEntity.setRatings(4.3f);
        movieEntity.setReleaseDate(LocalDate.of(2002,4,8));

        if(movieService.save(movieEntity))
        {
            System.out.println("Details saved");
        }else {
            System.out.println("Details not saved");
        }
        Integer id=2;
        if(movieService.deleteById(id))
        {
            System.out.println("Data is deleted id: "+id);
        }else {
            System.out.println("Data is not deleted id: "+id);
        }

        Integer updateId=1;
        Float ratings=4.0f;

        if(movieService.updateById(updateId,ratings))
        {
            System.out.println("Data is updated");
        }else {
            System.out.println("Data is not updated");
        }

        Optional<MovieEntity> movieEntity1=movieService.findByMovieName("kanchana");
        if(movieEntity1.isPresent())
        {
            System.out.println("Data is found");
            System.out.println(movieEntity1.get());
        }else {
            System.out.println("Data not present");
        }
        Optional<MovieEntity> movieEntity2=movieService.findByHeroName("karthick");
        if(movieEntity2.isPresent())
        {
            System.out.println("Data is found");
            System.out.println(movieEntity2.get());
        }else {
            System.out.println("Data is not found");
        }

        Optional<MovieEntity> movieEntity3=movieService.findByDirectorName("Ram");
        if(movieEntity3.isPresent())
        {
            System.out.println("Data is found");
            System.out.println(movieEntity3.get());
        } else {
            System.out.println("Data is not found");
        }

        DBConnection.closeResource();

    }
}
