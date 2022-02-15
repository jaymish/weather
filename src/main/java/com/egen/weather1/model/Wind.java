package com.egen.weather1.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Wind {
    @Id
    private String id;
    private Double speed;
    private Double degree;

    public Wind(){

        this.id= UUID.randomUUID().toString();
    }
}
