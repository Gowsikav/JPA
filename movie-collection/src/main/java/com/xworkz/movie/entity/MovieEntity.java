package com.xworkz.movie.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "movie_details")
@NamedQuery(name = "movieName",query = "select a from MovieEntity a where a.movieName=:name")
@NamedQuery(name = "directorName",query = "select a from MovieEntity a where a.directorName=:name")
@NamedQuery(name = "heroName",query = "select a from MovieEntity a where a.heroName=:name")
@NamedQuery(name = "findAll",query = "select a from MovieEntity a")
@NamedQuery(name = "findById",query = "select a from MovieEntity a where a.movieId=:id")
@NamedQuery(name = "updateByMovieName",
        query = "update MovieEntity a set a.directorName=:directorName,a.ratings=:ratings where a.movieId=:id and a.movieName=:movieName")
@NamedQuery(name="updateHeroName",
        query = "update MovieEntity a set a.heroName=:heroName where a.movieId=:id")
@NamedQuery(name="updateGenre",
        query = "update MovieEntity a set a.genre=:genre where a.movieName=:movieName and a.movieId=:id")

public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer movieId;
    @Column(name = "movie_name")
    private String movieName;
    @Column(name = "hero_name")
    private String heroName;
    @Column(name = "director_name")
    private String directorName;
    @Column(name = "genre")
    private String genre;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column(name = "ratings")
    private Float ratings;
}
