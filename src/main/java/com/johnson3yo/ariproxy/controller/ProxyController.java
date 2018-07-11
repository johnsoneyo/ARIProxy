/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.controller;

import ch.loway.oss.ari4java.generated.Channel;
import com.johnson3yo.ariproxy.service.ARIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("ari-proxy")
public class ProxyController {

    @Autowired
    @Qualifier(value = "lenzService")
    private ARIService service;

    @PostMapping("channels")
    public ResponseEntity createChannel(@RequestBody Payload payload) {
        Channel c = service.originate(payload);
        return new ResponseEntity<Channel>(c, HttpStatus.OK);
    }

}
