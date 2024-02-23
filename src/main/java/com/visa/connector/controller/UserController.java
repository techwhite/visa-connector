package com.visa.connector.controller;

import com.visa.connector.model.UserRequest;
import com.visa.connector.service.UserService;
import com.visa.connector.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
 provide user authorization and token generation abilities
 */

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserRequest user) {
        try {
            // validate input user info
            if(!StringUtils.hasLength(user.getName()) || !StringUtils.hasLength(user.getPassword())) {
                logger.error("UserName or Password is Empty");
                return ResponseEntity.badRequest().body("UserName or Password is Empty");
            }

            // call service layer
            boolean res = userService.checkUserNameAndPassword(user);
            if (!res) {
                logger.error("Invalid userName or Password");
                return ResponseEntity.badRequest().body("Invalid userName or Password");
            }

            return ResponseEntity.ok(JwtUtil.generateToken(user));
        } catch (Exception e) {
            logger.error("Some exception happend:", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
