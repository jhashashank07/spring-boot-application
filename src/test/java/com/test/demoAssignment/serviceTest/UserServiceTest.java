package com.test.demoAssignment.serviceTest;

import com.test.demoAssignment.entity.user.User;
import com.test.demoAssignment.entity.user.UserRequest;
import com.test.demoAssignment.entity.user.UserResponse;
import com.test.demoAssignment.repository.UserRepository;
import com.test.demoAssignment.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mapstruct.control.MappingControl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Autowired
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    
    User mockUser = new User("1234", "Shashank", "shashank@gmail.com");

    UserRequest userRequest = new UserRequest("Shashank", "shashank@gmail.com");

    @Test
    public void addUserTest() throws Exception {

        Mockito.when(userRepository.add(Mockito.any(User.class))).thenReturn(mockUser);

        UserResponse userResponse = userService.addUser(userRequest);

        assertEquals(userResponse.getName(), userRequest.getName());

        assertEquals(userResponse.getEmail(), userRequest.getEmail());
    }

    @Test
    public void getUserTest() throws Exception {

        Mockito.when(userRepository.get(Mockito.anyString())).thenReturn(mockUser);

        UserResponse userResponse = userService.getUser("1234");

        assertEquals(userResponse.getUserId(), "1234");
    }

    @Test
    public void deleteUserTest() throws Exception {

        Mockito.when(userRepository.get(Mockito.anyString())).thenReturn(mockUser);

        Mockito.when(userRepository.remove(Mockito.anyString())).thenReturn(mockUser);

        UserResponse userResponse = userService.deleteUser("1234");

        assertEquals(userResponse.getUserId(), "1234");
    }

    @Test
    public void updateUserTest() throws Exception {

        User updatedUser = new User("1234", "Shashank Jha", "jhashashank944@gmail.com");

        Mockito.when(userRepository.get(Mockito.anyString())).thenReturn(mockUser);

        Mockito.when(userRepository.update(Mockito.anyString(), Mockito.any(User.class))).thenReturn(updatedUser);

        UserResponse userResponse = userService.updateUser("1234", userRequest);

        assertEquals(userResponse.getUserId(), "1234");

        assertEquals(userResponse.getName(), "Shashank Jha");
    }

}
