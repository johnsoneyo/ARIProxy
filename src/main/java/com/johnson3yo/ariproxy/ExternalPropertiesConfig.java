/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author johnson3yo
 */
@Configuration
@ComponentScan({"com.johnon3yo.ariproxy"})
@PropertySource(value = {"file:/usr/ariproxy/application.properties"})
public class ExternalPropertiesConfig {
    
}
