package es.jugmadrid.springboot3.controller;

import es.jugmadrid.springboot3.controller.criteria.CarsSearchCriteria;
import es.jugmadrid.springboot3.mapper.CarMapper;
import es.jugmadrid.springboot3.model.CarDto;
import es.jugmadrid.springboot3.model.CarsPageResponse;
import es.jugmadrid.springboot3.service.CarFilterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

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
        return carFilterService.searchCars(carMapper.toFilter(criteria), criteria.getPage(), criteria.getSize());
    }

}