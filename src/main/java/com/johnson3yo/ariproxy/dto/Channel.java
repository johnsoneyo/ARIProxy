/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.dto;

/**
 *
 * @author johnson3yo
 */
public class Channel {

    private String id;
    private String name;
    private String state;
    private Caller caller;
    private Connected connected;
    private String accountcode;
    private DialPlan dialplan;
    private String creationtime;
    private String language;

    public Channel() {
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Caller getCaller() {
        return caller;
    }

    public void setCaller(Caller caller) {
        this.caller = caller;
    }

    public Connected getConnected() {
        return connected;
    }

    public void setConnected(Connected connected) {
        this.connected = connected;
    }

    public String getAccountcode() {
        return accountcode;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }

    public DialPlan getDialplan() {
        return dialplan;
    }

    public void setDialplan(DialPlan dialplan) {
        this.dialplan = dialplan;
    }

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    
    
    

   private class Caller {

        String name;
        String number;

        public Caller() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
        
        
    }

   private class Connected {

        String name;
        String number;

        public Connected() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
        
    }

   private class DialPlan {

        String context;
        String exten;
        String priority;

        public DialPlan() {
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getExten() {
            return exten;
        }

        public void setExten(String exten) {
            this.exten = exten;
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }
        

    }

}
