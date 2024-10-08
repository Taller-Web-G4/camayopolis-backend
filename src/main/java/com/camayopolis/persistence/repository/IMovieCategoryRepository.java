package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.MovieCategoryEntity;
import com.camayopolis.persistence.entity.MovieCategoryEntityId;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface IMovieCategoryRepository extends ListCrudRepository<MovieCategoryEntity, MovieCategoryEntityId> {
    List<MovieCategoryEntity> findMovieCategoryEntitiesByCatId(Integer catId);
    List<MovieCategoryEntity> findMovieCategoryEntitiesByPelId(Integer pelId);
}
