package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.MovieLanguageEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface IMovieLanguageRepository extends ListCrudRepository<MovieLanguageEntity, Integer> {
}