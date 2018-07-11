/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.service;

import ch.loway.oss.ari4java.generated.Channel;
import com.johnson3yo.ariproxy.controller.Payload;

/**
 *
 * @author johnson3yo
 */
public interface ARIService {
    Channel originate(Payload p);
}
