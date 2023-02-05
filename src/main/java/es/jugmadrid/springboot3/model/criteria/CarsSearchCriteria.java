package es.jugmadrid.springboot3.model.criteria;

import es.jugmadrid.springboot3.model.FuelType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CarsSearchCriteria {

    private String brand;
    private String model;
    private FuelType fuelType;
    private Integer numberOfDoors;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private int page = 0;
    private int size = 10;

}