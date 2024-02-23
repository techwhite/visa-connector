package com.visa.connector.service;

import com.visa.connector.model.UserRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*
This service is to authenticate input username and password. These info can be compared with db saved in backend
environment. Might have ACL and Role validation.
 */
@Service
public class UserService {
    // todo: save into more secure places
    @Value("${login.username}")
    private String userName;

    @Value("${login.password}")
    private String password;

    public boolean checkUserNameAndPassword(UserRequest user) {
        // todo: ACL and role check

        return userName.equals(user.getName()) && password.equals(user.getPassword());
    }
}
