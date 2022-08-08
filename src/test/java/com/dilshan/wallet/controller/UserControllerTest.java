package com.dilshan.wallet.controller;

import com.dilshan.wallet.dto.UserDto;
import com.dilshan.wallet.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService service;

    @Test
    public void createShouldReturnMessageFromService() throws Exception {
        // given
        UserDto userDto = new UserDto();
        userDto.setAmount(100000);
        userDto.setCurrency("USD");

        when(service.create(userDto)).thenReturn(true);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/create").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("true")));
    }
}