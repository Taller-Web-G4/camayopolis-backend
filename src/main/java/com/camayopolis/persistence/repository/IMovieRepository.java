package com.camayopolis.persistence.repository;

import com.camayopolis.persistence.entity.MovieEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface IMovieRepository extends ListCrudRepository<MovieEntity, Integer> {
    List<MovieEntity> findByPelEnCartelera(boolean enCartelera);
    List<MovieEntity> findByPelEsEstrenoProximo(boolean esEstrenoProximo);
    List<MovieEntity> findByGen_Id(Integer genderId);
    List<MovieEntity> findByPelFechaEstrenoAfter(LocalDate date, Sort sort);
    List<MovieEntity> findByPelFechaEstrenoBefore(LocalDate date, Sort sort);
    List<MovieEntity> findByPelEnCarteleraTrueOrderByPelFechaEstrenoAsc();
}