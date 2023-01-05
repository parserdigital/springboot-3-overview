package es.jugmadrid.springboot3.dao.repository;

import es.jugmadrid.springboot3.controller.criteria.FuelType;
import es.jugmadrid.springboot3.dao.Car;
import es.jugmadrid.springboot3.utils.TestFactoryUtils;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("/insert_test_data.sql")
@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CarRepositoryTest {

    @Autowired
    private CarRepository underTest;

    @Test
    void givenDataset_whenFindAllWithNoFilter_thenAllResultsAreReturned() {
        Specification<Car> spec = TestFactoryUtils.getEmptyFilterSpec();
        Page<Car> pageResult = underTest.findAll(spec, PageRequest.of(0, 100000));

        assertThat(pageResult).isNotNull();
        assertThat(pageResult.getTotalElements()).isEqualTo(5);
    }

    @Test
    void givenDataset_whenFindAllByBrand_thenFilterWorksAsExpected() {
        Specification<Car> spec = TestFactoryUtils.getFilterByBrandSpec("audi");
        Page<Car> pageResult = underTest.findAll(spec, PageRequest.of(0, 100000));

        assertThat(pageResult).isNotNull();
        assertThat(pageResult.getContent())
                .extracting(Car::getBrand)
                .contains("audi")
                .doesNotContain("bmw", "peugeot", "nissan");
    }

    @Test
    void givenDataset_whenFindAllByModel_thenFilterWorksAsExpected() {
        Specification<Car> spec = TestFactoryUtils.getFilterByModelSpec("m4");
        Page<Car> pageResult = underTest.findAll(spec, PageRequest.of(0, 100000));

        assertThat(pageResult).isNotNull();
        assertThat(pageResult.getContent())
                .extracting(Car::getModel)
                .contains("m4")
                .doesNotContain("audi", "peugeot", "nissan");
    }

    @ParameterizedTest
    @MethodSource("getFuelTypeUseCases")
    void givenDataset_whenFindAllByFuelType_thenFilterWorksAsExpected(FuelType fuelType, int expectedElements) {
        Specification<Car> spec = TestFactoryUtils.getFilterByFuelTypeSpec(fuelType);
        Page<Car> pageResult = underTest.findAll(spec, PageRequest.of(0, 100000));

        assertThat(pageResult).isNotNull();
        assertThat(pageResult.getTotalElements()).isEqualTo(expectedElements);
        assertThat(pageResult.getContent())
                .hasSize(expectedElements)
                .extracting(Car::getFuelType)
                .contains(fuelType);
    }

    @Test
    void givenDataset_whenFindAllByNumberOfDoors_thenFilterWorksAsExpected() {
        Specification<Car> spec = TestFactoryUtils.getFilterByNumberOfDoorsSpec(3);
        Page<Car> pageResult = underTest.findAll(spec, PageRequest.of(0, 100000));

        assertThat(pageResult).isNotNull();
        assertThat(pageResult.getTotalElements()).isEqualTo(1);
        assertThat(pageResult.getContent())
                .hasSize(1)
                .extracting(Car::getNumberOfDoors)
                .contains(3);
    }

    @Test
    void givenDataset_whenFindAllByPriceRange_thenFilterWorksAsExpected() {
        BigDecimal priceFrom = new BigDecimal("15000");
        BigDecimal priceTo = new BigDecimal("30000");
        Specification<Car> spec = TestFactoryUtils.getFilterByPriceRangeSpec(priceFrom, priceTo);
        Page<Car> pageResult = underTest.findAll(spec, PageRequest.of(0, 100000));

        assertThat(pageResult).isNotNull();
        assertThat(pageResult.getTotalElements()).isEqualTo(2);
        assertThat(pageResult.getContent())
                .hasSize(2)
                .extracting(Car::getPrice)
                .have(priceBetween(priceFrom, priceTo));
    }

    private static Condition<BigDecimal> priceBetween(BigDecimal priceFrom, BigDecimal priceTo) {
        return new Condition<>(val -> val.compareTo(priceFrom) > 0 && val.compareTo(priceTo) < 0, "priceComparator");
    }

    private static Stream<Arguments> getFuelTypeUseCases() {
        return Stream.of(
                Arguments.of(FuelType.DIESEL, 2),
                Arguments.of(FuelType.GASOLINE, 3)
        );
    }

}