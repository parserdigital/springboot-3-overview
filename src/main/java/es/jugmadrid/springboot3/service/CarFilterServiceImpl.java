package es.jugmadrid.springboot3.service;

import es.jugmadrid.springboot3.dao.Car;
import es.jugmadrid.springboot3.dao.filter.CarsFilter;
import es.jugmadrid.springboot3.dao.repository.CarRepository;
import es.jugmadrid.springboot3.dao.specification.CarSpec;
import es.jugmadrid.springboot3.mapper.CarMapper;
import es.jugmadrid.springboot3.model.CarDto;
import es.jugmadrid.springboot3.model.CarsPageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CarFilterServiceImpl implements CarFilterService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarFilterServiceImpl(CarRepository carRepository,
                                CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public CarsPageResponse<CarDto> searchCars(CarsFilter filter, int page, int size) {
        Specification<Car> spec = CarSpec.filterBy(filter);
        Page<Car> pageResult = carRepository.findAll(spec, PageRequest.of(page, size));
        return carMapper.toCarsPageResponse(pageResult);
    }
}