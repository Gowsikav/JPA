package com.xworkz.movie.runner;

import com.xworkz.movie.entity.MovieEntity;
import com.xworkz.movie.service.MovieService;
import com.xworkz.movie.service.MovieServiceImpl;
import com.xworkz.movie.util.DBConnection;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MovieRunner {
    public static void main(String[] args) {
        MovieService movieService=new MovieServiceImpl();
//        MovieEntity movieEntity=new MovieEntity();
//        movieEntity.setMovieName("Bheem");
//        movieEntity.setGenre("Thriller");
//        movieEntity.setDirectorName("Deepak");
//        movieEntity.setHeroName("Rajesh");
//        movieEntity.setRatings(4.3f);
//        movieEntity.setReleaseDate(LocalDate.of(2002,4,8));
//
//        if(movieService.save(movieEntity))
//        {
//            System.out.println("Details saved");
//        }else {
//            System.out.println("Details not saved");
//        }
//        Integer id=2;
//        if(movieService.deleteById(id))
//        {
//            System.out.println("Data is deleted id: "+id);
//        }else {
//            System.out.println("Data is not deleted id: "+id);
//        }
//
//        Integer updateId=1;
//        Float ratings=4.0f;
//
//        if(movieService.updateById(updateId,ratings))
//        {
//            System.out.println("Data is updated");
//        }else {
//            System.out.println("Data is not updated");
//        }
//
//        Optional<MovieEntity> movieEntity1=movieService.findByMovieName("kanchana");
//        if(movieEntity1.isPresent())
//        {
//            System.out.println("Data is found");
//            System.out.println(movieEntity1.get());
//        }else {
//            System.out.println("Data not present");
//        }
//
//        Optional<MovieEntity> movieEntity3=movieService.findByDirectorName("Ram");
//        if(movieEntity3.isPresent())
//        {
//            System.out.println("Data is found");
//            System.out.println(movieEntity3.get());
//        } else {
//            System.out.println("Data is not found");
//        }
//
//        List<MovieEntity> movieEntity2=movieService.findByHeroName("karthick");
//        if(!movieEntity2.isEmpty())
//        {
//            System.out.println("Data is found");
//           movieEntity2.forEach(System.out::println);
//        }else {
//            System.out.println("Data is not found");
//        }

        List<MovieEntity> list=movieService.findAll();
        if(!list.isEmpty())
        {
            System.out.println("Data is found");
            list.forEach(System.out::println);
        }else {
            System.out.println("Data is not found");
        }

//        Integer findId=4;
//        MovieEntity movie=movieService.findById(findId);
//        if(movie!=null)
//        {
//            System.out.println("data is found");
//            System.out.println(movie);
//        }else {
//            System.out.println("Data is not found");
//        }

//        MovieEntity movieEntity1=movieService.updateByMovieName("Bheem",7,"Kumar",3.4f);
//        System.out.println(movieEntity1);
//
//        MovieEntity movieEntity2=movieService.updateGenre("Action","comedy",5);
//        System.out.println(movieEntity2);
//
//        MovieEntity movieEntity3=movieService.updateHeroName(7,"Sathish");
//        System.out.println(movieEntity3);

//        List<String> list1=movieService.getAllMovieName();
//        list1.forEach(System.out::println);

//        List<Float> list2=movieService.getAllRatings();
//        list2.forEach(System.out::println);

        List<Object> objects=movieService.getAllReleaseDate();
        objects.forEach(System.out::println);

        List<Object[]> name=movieService.getMovieNameAndHeroName();
        name.stream().map(e->e[0]+" : "+e[1]).forEach(System.out::println);

        DBConnection.closeResource();
    }
}
