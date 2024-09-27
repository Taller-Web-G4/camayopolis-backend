package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.MovieFormatEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface IMovieFormatRepository extends ListCrudRepository<MovieFormatEntity, Integer> {
}