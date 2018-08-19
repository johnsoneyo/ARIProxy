/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;

/**
 *
 * @author johnson3yo
 */
public class GoogleHttpHeaders {

    public HttpHeaders getHeaders() throws IOException {

        Process b = Runtime.getRuntime().exec(new String[]{"bash", "-c", "echo $(gcloud auth application-default print-access-token)"});
        String result = new BufferedReader(new InputStreamReader(b.getInputStream())).lines()
                .parallel().collect(Collectors.joining("\n"));
        return new HttpHeaders() {
            {
                String authHeader = "Bearer " + result;
                set("Authorization", authHeader);
                set("Content-Type", "application/json; charset=utf-8");
            }
        };

    }

}
