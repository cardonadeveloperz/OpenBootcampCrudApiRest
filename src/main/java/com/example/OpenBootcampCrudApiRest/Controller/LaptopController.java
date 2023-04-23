package com.example.OpenBootcampCrudApiRest.Controller;

import com.example.OpenBootcampCrudApiRest.Entity.LaptopEntity;
import com.example.OpenBootcampCrudApiRest.Repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class LaptopController {

    @Autowired
    LaptopRepository repository;

    @GetMapping("/laptops")
    public ResponseEntity getAllUsers() {
        ResponseEntity response;
        List<LaptopEntity> laptops = repository.findAll();
        return (laptops.size() != 0) ? new ResponseEntity<>(repository.findAll(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("laptop")
    public ResponseEntity createLaptop(@RequestBody LaptopEntity laptop){
        return (new ResponseEntity<LaptopEntity>(repository.save(laptop), HttpStatus.OK));
    }
}
