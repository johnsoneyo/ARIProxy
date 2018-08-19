/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author johnson3yo
 */
public class EndpointResponse {

    private String technology;
    private String resource;
    private String state;
    @JsonProperty("channel_ids")
    private String[] channelIds;
    private CurrentChannel currentChannel;

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String[] getChannelIds() {
        return channelIds;
    }

    public void setChannelIds(String[] channelIds) {
        this.channelIds = channelIds;
    }

    public CurrentChannel getCurrentChannel() {
        return currentChannel;
    }

    public void setCurrentChannel(CurrentChannel currentChannel) {
        this.currentChannel = currentChannel;
    }
    
    

    public  class CurrentChannel {

        private String id;
        private String name;

        public CurrentChannel() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
