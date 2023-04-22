package com.example.OpenBootcampCrudApiRest.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.PrivateKey;

@Entity
@Getter
@Setter
public class LaptopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String model;
    private String date;
    private String brand;


    public LaptopEntity(String model, String date, String brand){
        this.model = model;
        this.date = date;
        this.brand = brand;
    }


}
