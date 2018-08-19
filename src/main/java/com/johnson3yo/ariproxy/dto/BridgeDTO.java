/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.dto;

import javax.validation.constraints.NotNull;

/**
 *
 * @author johnson3yo
 */
public class BridgeDTO {
    @NotNull
    private BridgeType type;
    @NotNull
    private String name;

    public BridgeDTO() {
    }

    public BridgeDTO(BridgeType bridgeType, String name) {
        this.type = bridgeType;
        this.name = name;
    }

    public BridgeType getType() {
        return type;
    }

    public void setType(BridgeType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
