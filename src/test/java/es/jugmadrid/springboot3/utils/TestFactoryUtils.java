package es.jugmadrid.springboot3.utils;

import es.jugmadrid.springboot3.model.CreateCarRequest;
import es.jugmadrid.springboot3.model.FuelType;
import es.jugmadrid.springboot3.dao.Car;
import es.jugmadrid.springboot3.dao.filter.CarsFilter;
import es.jugmadrid.springboot3.dao.specification.CarSpec;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class TestFactoryUtils {

    public static Specification<Car> getEmptyFilterSpec() {
        return CarSpec.filterBy(buildFilter(null, null, null, null, null, null));
    }

    public static Specification<Car> getFilterByBrandSpec(String brand) {
        return CarSpec.filterBy(buildFilter(brand, null, null, null, null, null));
    }

    public static Specification<Car> getFilterByModelSpec(String model) {
        return CarSpec.filterBy(buildFilter(null, model, null, null, null, null));
    }

    public static Specification<Car> getFilterByFuelTypeSpec(FuelType fuelType) {
        return CarSpec.filterBy(buildFilter(null, null, fuelType, null, null, null));
    }

    public static Specification<Car> getFilterByNumberOfDoorsSpec(int numberOfDoors) {
        return CarSpec.filterBy(buildFilter(null, null, null, numberOfDoors, null, null));
    }

    public static Specification<Car> getFilterByPriceRangeSpec(BigDecimal priceFrom, BigDecimal priceTo) {
        return CarSpec.filterBy(buildFilter(null, null, null, null, priceFrom, priceTo));
    }

    public static CreateCarRequest buildCreateCarRequest() {
        CreateCarRequest carRequest = new CreateCarRequest();
        carRequest.setBrand("BMW");
        carRequest.setModel("330i");
        carRequest.setPrice(new BigDecimal(47000));
        carRequest.setFuelType(FuelType.GASOLINE);
        carRequest.setNumberOfDoors(4);
        return carRequest;
    }

    public static Car buildCar(String brand, String model, BigDecimal price, FuelType fuelType, int numberOfDoors) {
        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setPrice(price);
        car.setFuelType(fuelType);
        car.setNumberOfDoors(numberOfDoors);
        return car;
    }

    private static CarsFilter buildFilter(String brand, String model, FuelType fuelType, Integer numberOfDoors,
                                          BigDecimal priceFrom, BigDecimal priceTo) {
        return new CarsFilter(brand, model, fuelType, numberOfDoors, priceFrom, priceTo);
    }
}
