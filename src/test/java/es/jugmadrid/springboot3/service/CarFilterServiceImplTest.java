package es.jugmadrid.springboot3.service;

import es.jugmadrid.springboot3.controller.criteria.FuelType;
import es.jugmadrid.springboot3.dao.Car;
import es.jugmadrid.springboot3.dao.filter.CarsFilter;
import es.jugmadrid.springboot3.dao.repository.CarRepository;
import es.jugmadrid.springboot3.exception.ErrorCode;
import es.jugmadrid.springboot3.exception.ServiceException;
import es.jugmadrid.springboot3.mapper.CarMapperImpl;
import es.jugmadrid.springboot3.model.CarDto;
import es.jugmadrid.springboot3.model.CarsPageResponse;
import io.micrometer.observation.ObservationRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static es.jugmadrid.springboot3.controller.criteria.FuelType.GASOLINE;
import static es.jugmadrid.springboot3.utils.TestFactoryUtils.buildCar;
import static es.jugmadrid.springboot3.utils.TestFactoryUtils.buildCreateCarRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarFilterServiceImplTest {

    @Mock
    private CarRepository carRepository;

    private CarFilterServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new CarFilterServiceImpl(carRepository, new CarMapperImpl(), ObservationRegistry.NOOP);
    }

    @Test
    void givenFilterIsSet_whenSearchCarsPageResponse_thenCarsAreReturned() {
        when(carRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(getCarPage());

        // It doesn't matter what values we use to configure the filter, it's mocked
        CarsPageResponse<CarDto> cars = underTest.searchCars(new CarsFilter(
                "brand", "model", null, 0, BigDecimal.ZERO, BigDecimal.TEN), 0, 10);

        assertThat(cars).isNotEmpty();
        assertThat(cars.getSize()).isEqualTo(3);
        assertThat(cars.getTotalSize()).isEqualTo(3);
        assertThat(cars.getContent().get(0).getBrand()).isEqualTo("audi");
        assertThat(cars.getContent().get(1).getBrand()).isEqualTo("ferrari");
        assertThat(cars.getContent().get(2).getBrand()).isEqualTo("volkswagen");
    }

    @Test
    void givenExistingCar_whenSearchById_thenCarIsReturned() {
        when(carRepository.findById(any()))
                .thenReturn(Optional.of(buildCar("audi", "a4", new BigDecimal("30000"), GASOLINE, 4)));

        CarDto car = underTest.searchCar(1);
        assertThat(car).isNotNull();
        assertThat(car.getBrand()).isEqualTo("audi");
        assertThat(car.getModel()).isEqualTo("a4");
        assertThat(car.getPrice()).isEqualTo(new BigDecimal("30000"));
        assertThat(car.getFuelType()).isEqualTo(GASOLINE);
        assertThat(car.getNumberOfDoors()).isEqualTo(4);
    }

    @Test
    void givenCarDoesNotExist_whenSearchById_thenExceptionIsThrown() {
        when(carRepository.findById(any())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ServiceException.class).isThrownBy(
                        () -> underTest.searchCar(1))
                .withMessage("no car found with id 1")
                .matches(e -> ErrorCode.RESOURCE_NOT_FOUND.equals(e.getErrorCode())
                );
    }

    @Test
    void givenNewCar_whenCreateCarIsCalled_thenCarIsCreated() {
        underTest.createCar(buildCreateCarRequest());

        ArgumentCaptor<Car> carArgumentCaptor = ArgumentCaptor.forClass(Car.class);

        verify(carRepository).save(carArgumentCaptor.capture());

        Car car = carArgumentCaptor.getValue();
        assertThat(car.getBrand()).isEqualTo("BMW");
        assertThat(car.getModel()).isEqualTo("330i");
        assertThat(car.getPrice()).isEqualTo(new BigDecimal(47000));
        assertThat(car.getFuelType()).isEqualTo(GASOLINE);
        assertThat(car.getNumberOfDoors()).isEqualTo(4);
    }

    private Page<Car> getCarPage() {
        List<Car> content = List.of(
                buildCar("audi", "a4", new BigDecimal("30000"), GASOLINE, 4),
                buildCar("ferrari", "La Ferrari", new BigDecimal("300000"), GASOLINE, 2),
                buildCar("volkswagen", "Passat", new BigDecimal("25000"), FuelType.DIESEL, 4));
        return new PageImpl<>(content);
    }

}