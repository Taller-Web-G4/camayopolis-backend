package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.MovieEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface IMovieRepository extends ListCrudRepository<MovieEntity, Integer> {
}