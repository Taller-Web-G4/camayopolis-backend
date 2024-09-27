package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.MovieCategoryEntity;
import com.camayopolis.persistence.entity.MovieCategoryEntityId;
import org.springframework.data.repository.ListCrudRepository;

public interface IMovieCategoryRepository extends ListCrudRepository<MovieCategoryEntity, MovieCategoryEntityId> {
}