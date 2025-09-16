package com.pragma.restaurant.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.restaurant.application.dto.request.CreateRestaurantRequestDto;
import com.pragma.restaurant.application.dto.response.RestaurantResponseDto;
import com.pragma.restaurant.application.handler.IRestaurantHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantRestController.class)
class RestaurantRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IRestaurantHandler restaurantHandler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateRestaurantAndReturn201() throws Exception {
        CreateRestaurantRequestDto dto = new CreateRestaurantRequestDto();
        dto.setName("El Corral");
        dto.setNit("123456789");
        dto.setAddress("Calle Falsa 123");
        dto.setPhone("+573004567890");
        dto.setUrlLogo("http://logo.url/img.png");
        dto.setIdOwner(1L);

        mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        verify(restaurantHandler).saveRestaurant(any(CreateRestaurantRequestDto.class));
    }

    @Test
    void shouldGetAllRestaurants() throws Exception {
        RestaurantResponseDto dto1 = new RestaurantResponseDto();
        dto1.setId(1L);
        dto1.setName("Restaurant A");
        dto1.setUrlLogo("logo_a.png");

        RestaurantResponseDto dto2 = new RestaurantResponseDto();
        dto2.setId(2L);
        dto2.setName("Restaurant B");
        dto2.setUrlLogo("logo_b.png");

        List<RestaurantResponseDto> restaurantList = Arrays.asList(dto1, dto2);

        when(restaurantHandler.getAllRestaurants()).thenReturn(restaurantList);

        mockMvc.perform(get("/restaurant"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Restaurant A"))
                .andExpect(jsonPath("$[1].id").value(2L));
    }
}