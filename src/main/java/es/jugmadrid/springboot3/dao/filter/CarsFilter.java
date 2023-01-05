package es.jugmadrid.springboot3.dao.filter;

import es.jugmadrid.springboot3.controller.criteria.FuelType;

import java.math.BigDecimal;

public record CarsFilter(String brand, String model, FuelType fuelType, Integer numberOfDoors, BigDecimal priceFrom, BigDecimal priceTo) {
}