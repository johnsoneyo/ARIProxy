/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.service;

import com.johnson3yo.ariproxy.datao.User;
import com.johnson3yo.ariproxy.dto.BridgeDTO;
import com.johnson3yo.ariproxy.dto.BridgeType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

/**
 *
 * @author johnson3yo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultAPIServiceTest {

    @Autowired
    @Qualifier(value = "default")
    private ARIService service;

    //@Test
    public void testGetBridges() {
        service.getBridges("holding");
    }

    //@Test
    public void testSaveBridge() {
        service.saveBridge(new BridgeDTO(BridgeType.holding, "resturant"));
    }

    //@Test
    public void testGetBridge() {
        service.getBridge("0e183689-6072-40eb-b896-7bafc3ab10d4");
    }

    @Test
    public void testFindUserByUserNameAndPassword() {
        User u = service.findUserByUsernameAndPassword("johnson", "johnson");
        Assert.isTrue(u != null, "user exits ");
    }
}
