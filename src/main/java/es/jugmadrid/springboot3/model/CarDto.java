package es.jugmadrid.springboot3.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class CarDto {

    private int id;
    private String brand;
    private String model;
    private FuelType fuelType;
    private int numberOfDoors;
    private BigDecimal price;

}