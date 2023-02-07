package es.jugmadrid.springboot3.controller;

import es.jugmadrid.springboot3.dao.repository.CarRepository;
import es.jugmadrid.springboot3.model.FuelType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static es.jugmadrid.springboot3.utils.TestFactoryUtils.buildCar;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CarRepository carRepository;

    @BeforeEach
    void setup(){
        insertTestData();
    }

    @Test
    void givenData_whenGetCarsFiltered_thenFilteredResponseExpected() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars")
                        .queryParam("priceFrom", "10000")
                        .queryParam("priceTo", "15000")
                        .queryParam("fuelType", "DIESEL"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content..fuelType", everyItem(is("DIESEL"))))
                .andExpect(jsonPath("$.content..price", everyItem(greaterThan(10000.00))))
                .andExpect(jsonPath("$.content..price", everyItem(lessThan(15000.00))));
    }

    private void insertTestData(){
        carRepository.save(buildCar("Seat", "Ibiza", BigDecimal.valueOf(14000.00), FuelType.DIESEL, 3));
        carRepository.save(buildCar("Seat", "León", BigDecimal.valueOf(24000.00), FuelType.DIESEL, 5));
    }
}