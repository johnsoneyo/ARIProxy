/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.datao;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author johnson3yo
 */
@Entity
@Table(name = "call_log")
public class CallLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "source")
    private String source;
    @Size(max = 16)
    @Column(name = "destination")
    private String destination;
    @Size(max = 64)
    @Column(name = "source_channel_id")
    private String sourceChannelId;
    @Size(max = 64)
    @Column(name = "destination_channel_id")
    private String destinationChannelId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss")
    @Column(name = "start_time")
    private Date startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss")
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "no_of_participants")
    private Integer noOfParticipants;
    @Transient
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date day;
    

    public CallLog() {
    }

    public CallLog(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getNoOfParticipants() {
        return noOfParticipants;
    }

    public void setNoOfParticipants(Integer noOfParticipants) {
        this.noOfParticipants = noOfParticipants;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSourceChannelId() {
        return sourceChannelId;
    }

    public void setSourceChannelId(String sourceChannelId) {
        this.sourceChannelId = sourceChannelId;
    }

    public String getDestinationChannelId() {
        return destinationChannelId;
    }

    public void setDestinationChannelId(String destinationChannelId) {
        this.destinationChannelId = destinationChannelId;
    }

    public Date getDay() {
        this.day = endTime;
        return day;
    }

  
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CallLog)) {
            return false;
        }
        CallLog other = (CallLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.johnson3yo.ariproxy.datao.CallLog[ id=" + id + " ]";
    }

}
