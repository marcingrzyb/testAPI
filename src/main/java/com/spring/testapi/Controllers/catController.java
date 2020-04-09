package com.spring.testapi.Controllers;

import com.spring.testapi.Entities.catEntity;
import com.spring.testapi.Services.catService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@NoArgsConstructor
@RestController
public class catController {
    @Autowired
    private catService catService;

    @GetMapping(value="/cats")
    public ResponseEntity<List<catEntity>> getAllCats(){
        return catService.getAllCats();
    }
    @GetMapping("/cats/{id}")
    public ResponseEntity<Optional<catEntity>> getCat(@PathVariable Long id){
        return catService.getCat(id);
    }
    @PostMapping("/cats")
    public void addCat(@RequestBody catEntity catEntity){
        catService.addCat(catEntity);
    }
    @PutMapping("/cats/{id}")
    public void updateCat(@PathVariable Long id,@RequestBody catEntity catEntity){
        catService.updateCat(catEntity);
    }
    @DeleteMapping("/cats/{id}")
    public void deleteCat(@PathVariable Long id){
        catService.deleteCat(id);
    }
}
