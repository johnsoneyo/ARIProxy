/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.service;

import ch.loway.oss.ari4java.tools.ARIException;
import com.johnson3yo.ariproxy.controller.Payload;
import java.net.URISyntaxException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author johnson3yo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProxyServiceTest {
    
    @Autowired private LenzAPIService service;
    
    
    @Test
    public  void testOriginate() throws ARIException, URISyntaxException{
        service.originate(new Payload());
    }
    
}
