package es.jugmadrid.springboot3.dao.specification;

import es.jugmadrid.springboot3.controller.criteria.FuelType;
import es.jugmadrid.springboot3.dao.Car;
import es.jugmadrid.springboot3.dao.filter.CarsFilter;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class CarSpec {

    public static final String MAKE = "make";
    public static final String FUEL_TYPE = "fuelType";
    public static final String NUMBER_OF_DOORS = "numberOfDoors";
    public static final String PRICE = "price";

    private CarSpec() {
        //empty
    }

    public static Specification<Car> filterBy(CarsFilter carsFilter) {
        return Specification
                .where(hasMake(carsFilter.make()))
                .and(hasFuelType(carsFilter.fuelType()))
                .and(hasNumberOfDoors(carsFilter.numberOfDoors()))
                .and(hasPriceGreaterThan(carsFilter.priceFrom()))
                .and(hasPriceLessThan(carsFilter.priceTo()));
    }

    private static Specification<Car> hasMake(String make) {
        return ((root, query, cb) -> make == null || make.isEmpty() ? cb.conjunction() : cb.equal(root.get(MAKE), make));
    }

    private static Specification<Car> hasFuelType(FuelType fuelType) {
        return (root, query, cb) -> fuelType == null ? cb.conjunction() : cb.equal(root.get(FUEL_TYPE), fuelType);
    }

    private static Specification<Car> hasNumberOfDoors(Integer numberOfDoors) {
        return (root, query, cb) -> numberOfDoors == null ? cb.conjunction() : cb.equal(root.get(NUMBER_OF_DOORS), numberOfDoors);
    }

    private static Specification<Car> hasPriceGreaterThan(BigDecimal priceFrom) {
        return (root, query, cb) -> priceFrom == null ? cb.conjunction() : cb.greaterThan(root.get(PRICE), priceFrom);
    }

    private static Specification<Car> hasPriceLessThan(BigDecimal priceTo) {
        return (root, query, cb) -> priceTo == null ? cb.conjunction() : cb.lessThan(root.get(PRICE), priceTo);
    }
}