/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author johnson3yo
 */
public class BridgeResponse {

    private String id;
    private String technology;
    @JsonProperty(value = "bridge_type")
    private String bridgeType;
    @JsonProperty(value = "bridge_class")
    private String bridgeClass;
    private String creator;
    private String name;
    private List<String> channels;
    @JsonProperty(value = "video_mode")
    private String videoMode;
    private List<Node> nodes;

    public BridgeResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getBridgeType() {
        return bridgeType;
    }

    public void setBridgeType(String bridgeType) {
        this.bridgeType = bridgeType;
    }

    public String getBridgeClass() {
        return bridgeClass;
    }

    public void setBridgeClass(String bridgeClass) {
        this.bridgeClass = bridgeClass;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }

    public String getVideoMode() {
        return videoMode;
    }

    public void setVideoMode(String videoMode) {
        this.videoMode = videoMode;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
    

    public static class Node {

        public Node(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public Node() {
        }     
               
        private String id;
        private String name;
    
        private List<Children> children;

        public List<Children> getChildren() {
            return children;
        }

        public void setChildren(List<Children> children) {
            this.children = children;
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
        
        
        
        public static class Children {

            public Children() {
            }
            
            private String id;
            private String name;

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

}
