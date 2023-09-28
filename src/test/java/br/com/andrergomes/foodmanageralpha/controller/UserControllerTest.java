package br.com.andrergomes.foodmanageralpha.controller;

import br.com.andrergomes.foodmanageralpha.model.User;
import br.com.andrergomes.foodmanageralpha.service.UserService;
import br.com.andrergomes.foodmanageralpha.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.Media;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Test
    void findById() throws Exception {
        User user = userServiceImpl.findAll().get(0);

        given(userService.findById(any(UUID.class))).willReturn(user);

        mockMvc.perform(get("/v1/users/" + user.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(user.getId().toString())))
                .andExpect(jsonPath("$.name", is(user.getName())));
    }
}