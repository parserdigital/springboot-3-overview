package es.jugmadrid.springboot3.dao.specification;

import es.jugmadrid.springboot3.model.FuelType;
import es.jugmadrid.springboot3.dao.Car;
import es.jugmadrid.springboot3.dao.filter.CarsFilter;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class CarSpec {

    public static final String BRAND = "brand";
    public static final String MODEL = "model";
    public static final String FUEL_TYPE = "fuelType";
    public static final String NUMBER_OF_DOORS = "numberOfDoors";
    public static final String PRICE = "price";

    private CarSpec() {
        //empty
    }

    public static Specification<Car> filterBy(CarsFilter carsFilter) {
        return Specification
                .where(hasBrand(carsFilter.brand()))
                .and(hasModel(carsFilter.model()))
                .and(hasFuelType(carsFilter.fuelType()))
                .and(hasNumberOfDoors(carsFilter.numberOfDoors()))
                .and(hasPriceGreaterThan(carsFilter.priceFrom()))
                .and(hasPriceLessThan(carsFilter.priceTo()));
    }

    private static Specification<Car> hasBrand(String brand) {
        return ((root, query, cb) -> brand == null || brand.isEmpty() ? cb.conjunction() : cb.equal(root.get(BRAND), brand));
    }

    private static Specification<Car> hasModel(String model) {
        return ((root, query, cb) -> model == null || model.isEmpty() ? cb.conjunction() : cb.equal(root.get(MODEL), model));
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