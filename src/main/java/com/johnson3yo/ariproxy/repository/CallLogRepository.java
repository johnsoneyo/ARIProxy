/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.repository;

import com.johnson3yo.ariproxy.datao.CallLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author johnson3yo
 */
public interface CallLogRepository extends JpaRepository<CallLog,Integer>{
    
}
