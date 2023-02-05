package es.jugmadrid.springboot3.mapper;

import es.jugmadrid.springboot3.model.CreateCarRequest;
import es.jugmadrid.springboot3.model.criteria.CarsSearchCriteria;
import es.jugmadrid.springboot3.dao.Car;
import es.jugmadrid.springboot3.dao.filter.CarsFilter;
import es.jugmadrid.springboot3.model.CarDto;
import es.jugmadrid.springboot3.model.CarsPageResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        injectionStrategy = CONSTRUCTOR
)
public interface CarMapper {

    CarsFilter toFilter(CarsSearchCriteria searchCriteria);

    CarDto toCarDto(Car car);

    List<CarDto> toCarDtos(List<Car> cars);

    Car toCarEntity(CreateCarRequest createCarRequest);

    default CarsPageResponse<CarDto> toCarsPageResponse(Page<Car> page) {
        CarsPageResponse<CarDto> pageResponse = new CarsPageResponse<>();
        pageResponse.setContent(toCarDtos(page.getContent()));
        pageResponse.setPage(page.getNumber());
        pageResponse.setSize(page.getSize());
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setTotalSize(page.getTotalElements());
        return pageResponse;
    }

}