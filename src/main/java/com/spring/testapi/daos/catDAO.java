package com.spring.testapi.daos;

import com.spring.testapi.Entities.catEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface catDAO extends CrudRepository<catEntity, Long> {
    @Override
    Iterable<catEntity> findAll();

    @Override
    Optional<catEntity> findById(Long aLong);

    @Override
    <S extends catEntity> S save(S s);

    @Override
    void deleteById(Long aLong);

    @Override
    boolean existsById(Long aLong);
}

