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
public class PlaybackResponse {

    private String id;
    private String language;
    @JsonProperty(value = "media_uri")
    private String mediaURI;
    private String state;
    @JsonProperty(value = "target_uri")
    private String targetURI;

    public PlaybackResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMediaURI() {
        return mediaURI;
    }

    public void setMediaURI(String mediaURI) {
        this.mediaURI = mediaURI;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTargetURI() {
        return targetURI;
    }

    public void setTargetURI(String targetURI) {
        this.targetURI = targetURI;
    }

    

}
