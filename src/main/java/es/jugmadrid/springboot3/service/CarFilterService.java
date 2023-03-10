package es.jugmadrid.springboot3.service;

import es.jugmadrid.springboot3.model.CreateCarRequest;
import es.jugmadrid.springboot3.dao.filter.CarsFilter;
import es.jugmadrid.springboot3.model.CarDto;
import es.jugmadrid.springboot3.model.CarsPageResponse;

public interface CarFilterService {

    CarsPageResponse<CarDto> searchCars(CarsFilter filter, int page, int size);

    CarDto searchCar(Integer carId);

    CarDto createCar(CreateCarRequest createCarRequest);

    void deleteCar(Integer carId);
}