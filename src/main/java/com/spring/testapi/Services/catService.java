package com.spring.testapi.Services;

import com.spring.testapi.Entities.catEntity;
import com.spring.testapi.daos.catDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class catService {
    catDAO catDAO;

    @Autowired
    public catService(catDAO catDAO) {
        this.catDAO = catDAO;
    }

    public ResponseEntity<List<catEntity>> getAllCats() {
        List<catEntity> result = new ArrayList<catEntity>();
        catDAO.findAll().forEach(result::add);
        return new ResponseEntity<List<catEntity>>(result, HttpStatus.OK);
    }

    public ResponseEntity<Optional<catEntity>> getCat(Long id) {
        return new ResponseEntity<Optional<catEntity>>(catDAO.findById(id),HttpStatus.OK);
    }

    public ResponseEntity deleteCat(Long id) {
        catDAO.deleteById(id);
        if(catDAO.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity updateCat(catEntity catEntity) {
        catDAO.save(catEntity);
        if(catDAO.findById(catEntity.getId()).equals(catEntity)){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity addCat(catEntity catEntity) {
        catDAO.save(catEntity);
        if(!catDAO.existsById(catEntity.getId())) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else return new ResponseEntity<>(HttpStatus.OK);
    }
}
