/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.controller;

import com.johnson3yo.ariproxy.datao.User;
import com.johnson3yo.ariproxy.datao.UserActivity;
import com.johnson3yo.ariproxy.service.ARIService;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author johnson3yo
 */
@CrossOrigin(origins = "http://localhost:4200",
        allowedHeaders = {"Content-Type"},
        methods = {RequestMethod.POST,
            RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("auth")
public class AuthController {
    
    @Autowired
    private ARIService service;
    
    @PostMapping
    public ResponseEntity login(@Valid @RequestBody User user) {
        User u = service.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (u == null) {
            return new ResponseEntity<String>("user not found", HttpStatus.NOT_FOUND);
        }
        UserActivity ua = new UserActivity.UserActivityBuilder().
                setId(Integer.SIZE).setSummary("logged in").
                setActivityType(UserActivity.ActivityType.LOGIN).setTimeCreated(new Date()).setUserId(u).build();
        service.saveActivity(ua);
        return new ResponseEntity<User>(u, HttpStatus.OK);
    }
    
}
