package com.spring.testapi.Controllers;

import com.spring.testapi.Entities.dogEntity;
import com.spring.testapi.Services.dogService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
@NoArgsConstructor
@RestController
public class dogController {
    @Autowired
    dogService dogService;

    @GetMapping("/dogs")
    public ResponseEntity<List<dogEntity>> getAllDogs(){
        return dogService.getAllDogs();
    }
    @GetMapping("/dogs/{id}")
    public ResponseEntity<Optional<dogEntity>> getDog(@PathVariable Long id){
        return dogService.getDog(id);
    }
    @PostMapping("/dogs")
    public void addDog(@RequestBody dogEntity dog, @RequestBody MultipartFile file){
        dogService.addDog(dog,file);
    }
    @PutMapping("/dogs/{id}")
    public void updateDog(@PathVariable Long id,@RequestBody dogEntity dogEntity){
        dogService.updateDog(dogEntity);
    }
    @DeleteMapping("/dogs/{id}")
    public void deleteDog(@PathVariable Long id){
        dogService.deleteDog(id);
    }
    @PostMapping("/dogs/{id}/addphoto")
    public void addPhoto(@PathVariable Long id,@RequestBody MultipartFile file){
        dogService.addPhoto(id,file);
    }
}
