/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.johnson3yo.ariproxy.dto.Voices;
import com.johnson3yo.ariproxy.service.GoogleService.GoogleRequest.Voice;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author johnson3yo
 */
@Service
public class GoogleService extends GoogleHttpHeaders {

    @Value("${media.stream.synthesize}")
    private  String synthesize;

    @Autowired
    private RestTemplate restTemplate;
    private String s;
    @Value("${sound.output.dir}")
    private String soundOutputDIR;

    @PostConstruct
    public void geAuthHeaders() throws IOException {

    }

    public Object[] googleTTS(String text) throws IOException, InterruptedException {

        AudioResponse response = null;

        GoogleRequest google = new GoogleRequest();
        GoogleRequest.Input input = new GoogleRequest().new Input();
        input.setText(text);
        GoogleRequest.Voice voice = new GoogleRequest().new Voice();
        voice.setLanguageCode("en-AU");
        voice.setName("en-AU-Wavenet-C");
        voice.setSsmlGender("MALE");

        GoogleRequest.AudioConfig audioConfig = new GoogleRequest().new AudioConfig();
        audioConfig.setAudioEncoding("MP3");

        google.setInput(input);
        google.setAudioConfig(audioConfig);
        google.setVoice(voice);

        HttpEntity request = new HttpEntity(google, getHeaders());
        ResponseEntity<String> exchange = restTemplate.
                exchange(synthesize,
                        HttpMethod.POST, request, String.class);
        ObjectMapper mapper = new ObjectMapper();
        response = mapper.readValue(exchange.getBody(), AudioResponse.class);

        BufferedWriter writer = new BufferedWriter(new FileWriter(soundOutputDIR+"sound.txt"));
        writer.write(response.getAudioContent());
        writer.close();
        Process p = Runtime.getRuntime().exec(new String[]{"bash", "-c", "base64 "+soundOutputDIR+"sound.txt --decode > "+soundOutputDIR+"sound.mp3"});
        p.waitFor();
        File file = new File(soundOutputDIR+"sound.mp3");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return new Object[]{resource, file};
    }

    public Voices getVoices() throws IOException {
        HttpEntity request = new HttpEntity(getHeaders());
        ResponseEntity<String> exchange = restTemplate.
                exchange("https://texttospeech.googleapis.com/v1beta1/voices",
                        HttpMethod.GET, request, String.class);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(exchange.getBody(), Voices.class);

    }

    class GoogleRequest {

        private Input input;
        private Voice voice;
        private AudioConfig audioConfig;

        public GoogleRequest() {
        }

        public class Input {

            public Input() {
            }

            String text;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

        }

        class Voice {

            public Voice() {
            }

            String languageCode;
            String name;
            String ssmlGender;

            public String getLanguageCode() {
                return languageCode;
            }

            public void setLanguageCode(String languageCode) {
                this.languageCode = languageCode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSsmlGender() {
                return ssmlGender;
            }

            public void setSsmlGender(String ssmlGender) {
                this.ssmlGender = ssmlGender;
            }

        }

        class AudioConfig {

            public AudioConfig() {
            }

            String audioEncoding;

            public String getAudioEncoding() {
                return audioEncoding;
            }

            public void setAudioEncoding(String audioEncoding) {
                this.audioEncoding = audioEncoding;
            }

        }

        public Input getInput() {
            return input;
        }

        public void setInput(Input input) {
            this.input = input;
        }

        public Voice getVoice() {
            return voice;
        }

        public void setVoice(Voice voice) {
            this.voice = voice;
        }

        public AudioConfig getAudioConfig() {
            return audioConfig;
        }

        public void setAudioConfig(AudioConfig audioConfig) {
            this.audioConfig = audioConfig;
        }

    }

    static class AudioResponse {

        String audioContent;

        public AudioResponse() {
        }

        public String getAudioContent() {
            return audioContent;
        }

        public void setAudioContent(String audioContent) {
            this.audioContent = audioContent;
        }

    }

}
