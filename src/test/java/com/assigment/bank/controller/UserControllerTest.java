package com.assigment.bank.controller;

import com.assigment.bank.dto.UserDto;
import com.assigment.bank.model.ContactInformation;
import com.assigment.bank.model.User;
import com.assigment.bank.service.implServices.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl service;

    private ContactInformation contactInformation;

    @Before
    public void init() {
        contactInformation = ContactInformation.builder()
                .address("Street")
                .city("City")
                .country("Country")
                .email("email@gmail.com")
                .phoneNumber("+79218981929")
                .build();
    }

    @Test
    public void getAllCUsers() throws Exception {
        Assertions.assertNotNull(mockMvc);
        Assertions.assertNotNull(service);
        List<User> listUsers = new ArrayList<>();
        when(service.getAllUsers()).thenReturn(listUsers);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void createUser() throws Exception {
        String string = "User with number: " + 1 + " created!";
        UserDto userDto = new UserDto();
        userDto.setUserNumber(1L);
        userDto.setFirstName("Kirill");
        userDto.setLastName("Pavlovich");
        userDto.setStatus("enabled");
        userDto.setContactInformation(contactInformation);
        userDto.setBankId(1L);

        when(service.createUser(userDto)).thenReturn(1L);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/user/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding("UTF-8")
                        .content(toJson(userDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(string));
    }

    @Test
    public void getUser() throws Exception {
        User user1 = User.builder()
                .userNumber(1L)
                .firstName("Kirill")
                .lastName("Pavlovich")
                .status("enabled")
                .contactInformation(contactInformation)
                .build();

        when(service.getUsersByNumber(anyLong())).thenReturn(user1);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void deleteUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/user/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}