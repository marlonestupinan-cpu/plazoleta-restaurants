package com.pragma.restaurant.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.restaurant.application.dto.request.CreateDishRequestDto;
import com.pragma.restaurant.application.handler.IDishHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DishRestController.class)
class DishRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDishHandler dishHandler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateDishAndReturn201() throws Exception {
        CreateDishRequestDto dto = new CreateDishRequestDto();
        dto.setName("Ajiaco Santafereño");
        dto.setPrice(28000);
        dto.setDescription("Sopa típica de Bogotá, Colombia.");
        dto.setUrlImage("ajiaco.png");
        dto.setIdRestaurant(1L);
        dto.setIdCategory(2L);

        mockMvc.perform(post("/dish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        verify(dishHandler).saveDish(any(CreateDishRequestDto.class));
    }
}