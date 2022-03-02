package com.test.demoAssignment.service;

import com.test.demoAssignment.entity.user.User;
import com.test.demoAssignment.entity.user.UserMapper;
import com.test.demoAssignment.entity.user.UserRequest;
import com.test.demoAssignment.entity.user.UserResponse;
import com.test.demoAssignment.exception.UserNotFoundException;
import com.test.demoAssignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse addUser(UserRequest userRequest) {
        User user = UserMapper.INSTANCE.userRequestToUser(userRequest);
        User userDb = userRepository.add(user);
        UserResponse userResponse = UserMapper.INSTANCE.userToUserResponse(userDb);
        return userResponse;
    }

    public UserResponse getUser(String userId) {
        User userDb = userRepository.get(userId);
        System.out.println(userDb);
        if(ObjectUtils.isEmpty(userDb))
            throw new UserNotFoundException("userId is invalid");
        UserResponse userResponse = UserMapper.INSTANCE.userToUserResponse(userDb);
        return userResponse;
    }

    public UserResponse deleteUser(String userId) {
        User userDb = userRepository.get(userId);
        if(ObjectUtils.isEmpty(userDb))
            throw new UserNotFoundException("userId is invalid");
        User user = userRepository.remove(userId);
        UserResponse userResponse = UserMapper.INSTANCE.userToUserResponse(user);
        return userResponse;
    }

    public UserResponse updateUser(String userId, UserRequest userRequest) {
        User user = userRepository.get(userId);
        if(ObjectUtils.isEmpty(user))
            throw new UserNotFoundException("userId is invalid");
        User newUser = UserMapper.INSTANCE.userRequestToUser(userRequest);
        newUser.setEntityId(userId);
        User userDb = userRepository.update(userId, newUser);
        UserResponse userResponse = UserMapper.INSTANCE.userToUserResponse(userDb);
        return userResponse;
    }

}
