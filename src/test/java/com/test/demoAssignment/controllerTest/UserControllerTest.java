package com.test.demoAssignment.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demoAssignment.controller.UserController;
import com.test.demoAssignment.entity.user.UserRequest;
import com.test.demoAssignment.entity.user.UserResponse;
import com.test.demoAssignment.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest (UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    UserResponse mockUserResponse = new UserResponse("1234", "Shashank", "shashank@gmail.com");

    String exampleUserRequestJson = "{\"name\":\"Shashank\",\"email\":\"shashank@gmail.com\"}";

    @Test
    public void addUserTest() throws Exception {

        Mockito.when(userService.addUser(Mockito.any(UserRequest.class))).thenReturn(mockUserResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user")
                .accept(MediaType.APPLICATION_JSON).content(exampleUserRequestJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String expected = "{\"userId\":\"1234\",\"name\":\"Shashank\",\"email\":\"shashank@gmail.com\"}";

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        verify(userService).addUser(Mockito.any(UserRequest.class));

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void getUserTest() throws Exception {

        Mockito.when(userService.getUser(Mockito.anyString())).thenReturn(mockUserResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/{id}", new String("1234"))
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String expected = "{\"userId\":\"1234\",\"name\":\"Shashank\",\"email\":\"shashank@gmail.com\"}";

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        verify(userService).getUser(Mockito.anyString());

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void deleteUserTest() throws Exception {

        Mockito.when(userService.deleteUser(Mockito.anyString())).thenReturn(mockUserResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/user/{id}", new String("1234"))
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String expected = "{\"userId\":\"1234\",\"name\":\"Shashank\",\"email\":\"shashank@gmail.com\"}";

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        verify(userService).deleteUser(Mockito.anyString());

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void updateUserTest() throws Exception {

        UserResponse updatedMockUserResponse = new UserResponse("1234", "Shashank Jha", "jhashashank944@gmail.com");

        Mockito.when(userService.updateUser(Mockito.anyString(), Mockito.any(UserRequest.class))).thenReturn(updatedMockUserResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/user/{id}", new String("1234"))
                .accept(MediaType.APPLICATION_JSON).content(exampleUserRequestJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String expected = "{\"userId\":\"1234\",\"name\":\"Shashank Jha\",\"email\":\"jhashashank944@gmail.com\"}";

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        verify(userService).updateUser(Mockito.anyString(), Mockito.any(UserRequest.class));

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}
