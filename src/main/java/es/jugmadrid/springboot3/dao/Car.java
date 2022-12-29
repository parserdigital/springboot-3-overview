package es.jugmadrid.springboot3.dao;

import es.jugmadrid.springboot3.controller.criteria.FuelType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "cars")
@Data
public class Car {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "brand")
    private String make;

    @Column(name = "fuel_type")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column(name = "num_of_doors")
    private int numberOfDoors;

    @Column(name = "price")
    private BigDecimal price;
}