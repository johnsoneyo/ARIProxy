/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.service;

import com.johnson3yo.ariproxy.dto.Voices;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author johnson3yo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoogleAPITest {

    @Autowired
    private GoogleService gservice;

    //@Test
    public void testGoogleTokenService() throws IOException, InterruptedException {
        gservice.googleTTS("Hello Guys, "
                + " my apologies for the silence.  i have been busy and buried to my vision"
                + ",i recently got distracted from the course we have but its for the good,"
                + " i'm already done building the call transfer/queue system,"
                + "minor features are required , call logging and call recording feature,"
                + "the core has been developed"
                + " which is call bridging to an agent."
                + " call transfer from an agent to another agent."
                + " music on hold .news on hold."
                + " So while figuring out how to build a dynamic IVR especially for the cloud"
                + ",just recently i stumbled on VoiceXml and artificial intelligence markup language, this is just a quick synthesis so bolaji and yemi can have a feel , it will get better. ");
    }

    @Test
    public void testGetVoices() throws IOException {
        Voices voice = gservice.getVoices();
    }
}
