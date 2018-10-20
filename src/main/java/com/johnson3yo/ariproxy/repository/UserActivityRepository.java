/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.repository;

import com.johnson3yo.ariproxy.datao.UserActivity;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author johnson3yo
 */
public interface UserActivityRepository extends CrudRepository<UserActivity, Integer> {
    
}
