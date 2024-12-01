package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.MovieEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface IMovieRepository extends ListCrudRepository<MovieEntity, Integer> {
    List<MovieEntity> findByPelEnCartelera(boolean enCartelera);
    List<MovieEntity> findByPelEsEstrenoProximo(boolean esEstrenoProximo);
    List<MovieEntity> findByGen_Id(Integer genderId);
}