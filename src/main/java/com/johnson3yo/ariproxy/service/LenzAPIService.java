/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.service;

import ch.loway.oss.ari4java.ARI;
import ch.loway.oss.ari4java.AriVersion;
import ch.loway.oss.ari4java.generated.Channel;
import ch.loway.oss.ari4java.tools.ARIException;
import ch.loway.oss.ari4java.tools.RestException;
import ch.loway.oss.ari4java.tools.http.NettyHttpClient;
import com.johnson3yo.ariproxy.controller.Payload;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service(value = "lenzService")
public class LenzAPIService implements ARIService {

    @Value("${ari.host}")
    private String host;
    @Value("${ari.username}")
    private String username;
    @Value("${ari.password}")
    private String password;

    @Override
    public Channel originate(Payload p) {
        ARI ari = new ARI();
        NettyHttpClient hc = new NettyHttpClient();
        try {
            hc.initialize(host, username, password);
            ari.setHttpClient(hc);
            ari.setWsClient(hc);
            ari.setVersion(AriVersion.ARI_0_0_1);

            return ari.channels().originate(p.getEndpoint(),
                    p.getExtension(),
                    p.getContext(),
                    1, p.getApp(),
                    p.getAppArgs(),
                    p.getCallerId(),
                    p.getTimeout());
        } catch (Exception e) {
            if (e instanceof RestException) {
                System.out.println("----------couldnt parse json field -------- ");
            } else {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void createChannel() throws ARIException {

    }

}
