package com.spring.testapi.Services;

import com.spring.testapi.Entities.dogEntity;
import com.spring.testapi.Entities.dogEntity;
import com.spring.testapi.daos.dogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class dogService {
    @Autowired
    dogDAO dogDAO;

    @Autowired
    DocumentManagementService DMS;

    public ResponseEntity<List<dogEntity>> getAllDogs() {
        List<dogEntity> result = new ArrayList<dogEntity>();
        dogDAO.findAll().forEach(result::add);
        return new ResponseEntity<List<dogEntity>>(result, HttpStatus.OK);
    }

    public ResponseEntity<Optional<dogEntity>> getDog(Long id) {
        return new ResponseEntity<Optional<dogEntity>>(dogDAO.findById(id),HttpStatus.OK);
    }

    public ResponseEntity deleteDog(Long id) {
        if(DMS.deleteFile("/dog/",dogDAO.findById(id).get().getPhotoName())) {
            dogDAO.deleteById(id);
        }
        if(dogDAO.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity updateDog(dogEntity dogEntity) {
        dogDAO.save(dogEntity);
        if(dogDAO.findById(dogEntity.getId()).get().getId().equals(dogEntity.getId())){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity addDog(dogEntity dogEntity, MultipartFile multipartFile) {
        if(multipartFile!=null) {
            String fileName=multipartFile.getOriginalFilename();
            try {
                if(DMS.uploadFile("/dog/",fileName,DMS.convertMultiPartToFile(multipartFile))) {
                    dogEntity.setPhotoName(fileName);
                    dogDAO.save(dogEntity);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else dogDAO.save(dogEntity);
        if(!dogDAO.existsById(dogEntity.getId())) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    public ResponseEntity addPhoto(Long id,MultipartFile multipartFile){
        if(!multipartFile.isEmpty()) {
            String fileName = multipartFile.getOriginalFilename();
            dogEntity dogEntity = dogDAO.findById(id).get();
            try {
                if (DMS.uploadFile("/dog/", fileName, DMS.convertMultiPartToFile(multipartFile))) {
                    dogEntity.setPhotoName(fileName);
                    return updateDog(dogEntity);
                } else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
