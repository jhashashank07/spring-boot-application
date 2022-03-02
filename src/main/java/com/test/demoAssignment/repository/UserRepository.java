package com.test.demoAssignment.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.test.demoAssignment.entity.user.User;
import com.test.demoAssignment.entity.user.UserMapper;
import com.test.demoAssignment.entity.user.UserRequest;
import com.test.demoAssignment.entity.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public User add(User user) {
        dynamoDBMapper.save(user);
        return user;
    }

    public User get(String userId) {
        User user = dynamoDBMapper.load(User.class, userId);
        return user;
    }

    public User remove(String userId) {
        User user = dynamoDBMapper.load(User.class, userId);
        dynamoDBMapper.delete(user);
        return user;
    }

    public User update(String userId, User user) {
        dynamoDBMapper.save(user,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("entityId",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(userId)
                                )));
        return user;
    }
}
