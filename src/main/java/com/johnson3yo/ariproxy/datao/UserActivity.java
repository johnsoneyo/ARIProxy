/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.datao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author johnson3yo
 */
@Entity
@Table(name = "user_activity")
@NamedQueries({
    @NamedQuery(name = "UserActivity.findAll", query = "SELECT u FROM UserActivity u")})
public class UserActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 256)
    @Column(name = "summary")
    private String summary;
    @Column(name = "activity_type")
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;
    @Column(name = "time_created")
    @Temporal(TemporalType.DATE)
    private Date timeCreated;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private User userId;

    public UserActivity() {
    }
    
    public enum ActivityType {
        LOGIN
    }

    public UserActivity(Integer id, String summary, ActivityType activityType, Date timeCreated, User userId) {
        this.id = id;
        this.summary = summary;
        this.activityType = activityType;
        this.timeCreated = timeCreated;
        this.userId = userId;
    }
    
    

    public UserActivity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof UserActivity)) {
            return false;
        }
        UserActivity other = (UserActivity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.johnson3yo.ariproxy.datao.UserActivity[ id=" + id + " ]";
    }

    public static class UserActivityBuilder {

        private Integer id;
        private String summary;
        private ActivityType activityType;
        private Date timeCreated;
        private User userId;
        
        public UserActivityBuilder() {

        }

        public UserActivityBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public UserActivityBuilder setSummary(String summary) {
            this.summary = summary;
              return this;
        }

        public UserActivityBuilder setActivityType(ActivityType activityType) {
            this.activityType = activityType;
              return this;
        }

        public UserActivityBuilder setTimeCreated(Date timeCreated) {
            this.timeCreated = timeCreated;
              return this;
        }

        public UserActivityBuilder setUserId(User userId) {
            this.userId = userId;
              return this;
        }
        
        

        public UserActivity build() {
            return new UserActivity(id, summary, activityType, timeCreated, userId);
        }

    }

}
