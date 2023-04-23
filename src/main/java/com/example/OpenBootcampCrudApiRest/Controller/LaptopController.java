package com.example.OpenBootcampCrudApiRest.Controller;

import com.example.OpenBootcampCrudApiRest.Entity.LaptopEntity;
import com.example.OpenBootcampCrudApiRest.Repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
