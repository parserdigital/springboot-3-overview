package es.jugmadrid.springboot3.controller;

import es.jugmadrid.springboot3.controller.criteria.FuelType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateCarRequest {

    private String brand;
    private Integer numberOfDoors;
    private FuelType fuelType;
    private BigDecimal price;
}