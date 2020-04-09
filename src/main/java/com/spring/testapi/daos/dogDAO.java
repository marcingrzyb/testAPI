package com.spring.testapi.daos;

import com.spring.testapi.Entities.dogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface dogDAO extends CrudRepository<dogEntity, Long> {
    @Override
    Iterable<dogEntity> findAll();

    @Override
    Optional<dogEntity> findById(Long aLong);

    @Override
    <S extends dogEntity> S save(S s);

    @Override
    void deleteById(Long aLong);

    @Override
    boolean existsById(Long aLong);
}
