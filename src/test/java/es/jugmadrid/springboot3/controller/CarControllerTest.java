package es.jugmadrid.springboot3.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenData_whenGetCarsFiltered_thenFilteredResponseExpected() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars")
                        .queryParam("priceFrom", "10000")
                        .queryParam("priceTo", "15000")
                        .queryParam("fuelType", "DIESEL"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content..fuelType", everyItem(is("DIESEL"))))
                .andExpect(jsonPath("$.content..price", everyItem(greaterThan(10000))))
                .andExpect(jsonPath("$.content..price", everyItem(lessThan(15000))));
    }
}