package es.jugmadrid.springboot3.controller;

import es.jugmadrid.springboot3.controller.criteria.CarsSearchCriteria;
import es.jugmadrid.springboot3.mapper.CarMapper;
import es.jugmadrid.springboot3.model.CarDto;
import es.jugmadrid.springboot3.model.CarsPageResponse;
import es.jugmadrid.springboot3.service.CarFilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    Logger logger = LoggerFactory.getLogger(CarController.class);

    private final CarFilterService carFilterService;
    private final CarMapper carMapper;

    public CarController(CarFilterService carFilterService,
                         CarMapper carMapper) {
        this.carFilterService = carFilterService;
        this.carMapper = carMapper;
    }

    @GetMapping(value = "/cars")
    @ResponseStatus(HttpStatus.OK)
    CarsPageResponse<CarDto> getCars(CarsSearchCriteria criteria) {
        logger.info("Get list of existing cars");
        return carFilterService.searchCars(carMapper.toFilter(criteria), criteria.getPage(), criteria.getSize());
    }

    @GetMapping(value = "/cars/{id}")
    @ResponseStatus(HttpStatus.OK)
    CarDto getCars(@PathVariable Integer id) {
        logger.info("Get an existing car by id");
        return carFilterService.searchCar(id);
    }

    @PostMapping(value = "/cars")
    @ResponseStatus(HttpStatus.CREATED)
    CarDto createCar(@RequestBody CreateCarRequest request) {
        logger.info("Create a new car");
        return carFilterService.createCar(request);
    }
}