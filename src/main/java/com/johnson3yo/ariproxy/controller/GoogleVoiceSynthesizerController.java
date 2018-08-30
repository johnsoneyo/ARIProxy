/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.controller;

import com.johnson3yo.ariproxy.dto.Voices;
import com.johnson3yo.ariproxy.service.GoogleService;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author johnson3yo
 */
@CrossOrigin(origins = "http://localhost:4200",
        allowedHeaders = {"Content-Type"},
        methods = {RequestMethod.POST,
            RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("google-tts")
public class GoogleVoiceSynthesizerController {

    @Autowired
    private GoogleService service;

    @GetMapping
    public ResponseEntity googleTTS(@RequestParam("text") String text) throws IOException, InterruptedException {
        Object[] response = service.googleTTS(text);
        InputStreamResource resource = (InputStreamResource) response[0];
        File file = (File) response[1];
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=sound.mp3");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @GetMapping("stream")
    public void googleTTSStream(@RequestParam("text") String text,HttpServletResponse resp) throws IOException, InterruptedException {
        Object[] response = service.googleTTS(text);
        InputStreamResource resource = (InputStreamResource) response[0];
        resp.setContentType("audio/mpeg");
        StreamUtils.copy(resource.getInputStream(), resp.getOutputStream());
    }

    @GetMapping("voices")
    public ResponseEntity getVoices() throws IOException {
        Voices voices = service.getVoices();
        return new ResponseEntity<Voices>(voices, HttpStatus.OK);
    }

}
