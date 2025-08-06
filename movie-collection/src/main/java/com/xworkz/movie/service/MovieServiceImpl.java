package com.xworkz.movie.service;

import com.xworkz.movie.entity.MovieEntity;
import com.xworkz.movie.repository.MovieRepository;
import com.xworkz.movie.repository.MovieRepositoryImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository=new MovieRepositoryImpl();
    private Optional<MovieEntity> optionalMovieEntity;

    public MovieServiceImpl()
    {
        System.out.println("MovieServiceImpl constructor");
    }

    private boolean validateDetails(MovieEntity movieEntity)
    {
        System.out.println("validate details method in service");
        if(movieEntity!=null)
        {
            System.out.println("movie entity is valid");
        } else {
            System.out.println("movie entity is not valid");
            return false;
        }

        String name= movieEntity.getMovieName();
        if(name!=null && name.length()>=3 && name.length()<25)
        {
            System.out.println("movie name is valid");
        } else {
            System.out.println("movie name is not valid");
            return false;
        }

        String directorName=movieEntity.getDirectorName();
        if(directorName!=null && directorName.length()>=3 && directorName.length()<25)
        {
            System.out.println("director name is valid");
        } else {
            System.out.println("director name is not valid");
            return false;
        }

        String heroName=movieEntity.getHeroName();
        if(heroName!=null && heroName.length()>=3 && heroName.length()<25)
        {
            System.out.println("hero name is valid");
        } else {
            System.out.println("hero name is not valid");
            return false;
        }

        String genre=movieEntity.getGenre();
        if(genre!=null && genre.length()>2 && genre.length()<25)
        {
            System.out.println("genre is valid");
        } else {
            System.out.println("genre is not valid");
            return false;
        }

        LocalDate release=movieEntity.getReleaseDate();
        if(release!=null)
        {
            System.out.println("release date is valid");
        } else {
            System.out.println("release date is not valid");
            return false;
        }

        Float rating=movieEntity.getRatings();
        if(rating!=null && rating>0)
        {
            System.out.println("ratings is valid");
        } else {
            System.out.println("rating is not valid");
            return false;
        }
        System.out.println("All details are valid");
        return true;
    }

    @Override
    public boolean save(MovieEntity movieEntity) {
        System.out.println("save method in service");
        if(validateDetails(movieEntity))
        {
            return movieRepository.save(movieEntity);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        System.out.println("delete by id in service");
        if(id!=null && id>0)
        {
            System.out.println("id is valid");
            return movieRepository.deleteById(id);
        }else {
            System.out.println("id is not valid");
        }
        return false;
    }

    @Override
    public boolean updateById(Integer id, Float ratings) {
        System.out.println("update by id in service");
        if(id!=null && id>0)
        {
            System.out.println("id is valid");
            if(ratings!=null && ratings>0)
            {
                System.out.println("ratings is valid");
                return movieRepository.updateById(id,ratings);
            }
        }
        System.out.println("id or ratings is not valid");
        return false;
    }

    @Override
    public Optional<MovieEntity> findByMovieName(String movieName) {
        System.out.println("find by movie name in service");
        if(movieName!=null && movieName.length()>=3 && movieName.length()<25)
        {
            System.out.println("movie name is valid");
             optionalMovieEntity= movieRepository.findByMovieName(movieName);
            optionalMovieEntity.ifPresent(this::validateDetails);
            return optionalMovieEntity;
        }else {
            System.out.println("movie name is not valid");
        }
        return Optional.empty();
    }

    @Override
    public List<MovieEntity> findByHeroName(String heroName) {
        System.out.println("find by hero name in service");
        if(heroName!=null && heroName.length()>=3 && heroName.length()<25)
        {
            System.out.println("hero name is valid");
             return movieRepository.findByHeroName(heroName);
        }else {
            System.out.println("hero name is not valid");
        }
        return null;
    }

    @Override
    public Optional<MovieEntity> findByDirectorName(String directorName) {
        System.out.println("find by director name in service");
        if(directorName!=null && directorName.length()>=3 && directorName.length()<25)
        {
            System.out.println("director name is valid");
            optionalMovieEntity= movieRepository.findByDirectorName(directorName);
            optionalMovieEntity.ifPresent(this::validateDetails);
            return optionalMovieEntity;
        }else {
            System.out.println("director name is not valid");
        }
        return Optional.empty();
    }

    @Override
    public MovieEntity findById(Integer id) {

        System.out.println("find by id method in service");
        if(id!=null && id>0)
        {
            System.out.println("id is valid");
           return movieRepository.findById(id);
        }else {
            System.out.println("id is not valid");
        }
        return null;
    }

    @Override
    public List<MovieEntity> findAll() {
        System.out.println("findAll method in service");
        return movieRepository.findAll();
    }

    @Override
    public List<MovieEntity> findByGenre(String genre) {
        System.out.println("find by genre in service");
        if(genre!=null)
        {
            return movieRepository.findByGenre(genre);
        }else {
            System.out.println("genre is not valid");
        }
        return Collections.emptyList();
    }

    @Override
    public MovieEntity updateByMovieName(String movieName, Integer id, String directorName, float ratings) {
        System.out.println("updateByMovieName method in service");

       return movieRepository.updateByMovieName(movieName,id,directorName,ratings);

    }

    @Override
    public MovieEntity updateGenre(String movieName, String genre,Integer id) {
        System.out.println("updateGenre method in service");

        return movieRepository.updateGenre(movieName,genre,id);
    }

    @Override
    public MovieEntity updateHeroName(Integer id, String heroName) {
        System.out.println("updateHeroName method in service");

        return movieRepository.updateHeroName(id,heroName);
    }

    @Override
    public List<String> getAllMovieName() {
        System.out.println("getAll movieName in service");
        return movieRepository.getAllMovieName();
    }

    @Override
    public List<Float> getAllRatings() {
        System.out.println("getAllRatings in service");
        return movieRepository.getAllRatings();
    }

    @Override
    public List<Object> getAllReleaseDate() {
        System.out.println("getAllReleaseDate in service");
        return movieRepository.getAllReleaseDate();
    }

    @Override
    public List<Object[]> getMovieNameAndHeroName() {
        System.out.println("getMovieNameAndHeroName method in service");
        return movieRepository.getMovieNameAndHeroName();
    }
}
