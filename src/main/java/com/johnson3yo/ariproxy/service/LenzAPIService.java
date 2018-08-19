/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.service;

import ch.loway.oss.ari4java.ARI;
import ch.loway.oss.ari4java.AriVersion;
import ch.loway.oss.ari4java.generated.Channel;
import ch.loway.oss.ari4java.tools.RestException;
import ch.loway.oss.ari4java.tools.http.NettyHttpClient;
import com.johnson3yo.ariproxy.dto.BridgeDTO;
import com.johnson3yo.ariproxy.dto.BridgeEagerLoaded;
import com.johnson3yo.ariproxy.dto.BridgeResponse;
import com.johnson3yo.ariproxy.dto.EndpointResponse;
import com.johnson3yo.ariproxy.dto.PayloadDTO;
import com.johnson3yo.ariproxy.dto.PlaybackResponse;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
    
    private  static ARI ari ;
    
    
    @PostConstruct
    public void initNetty(){
            ari = new ARI();
        NettyHttpClient hc = new NettyHttpClient();
      
        try {
            hc.initialize(host, username, password);
              ari.setHttpClient(hc);
            ari.setWsClient(hc);
            ari.setVersion(AriVersion.ARI_0_0_1);
        } catch (URISyntaxException ex) {
            Logger.getLogger(LenzAPIService.class.getName()).log(Level.SEVERE, null, ex);
        }
          

         
    }

    @Override
    public Channel originate(PayloadDTO p)throws RestException {
       
             return ari.channels().originate(p.getEndpoint(),
                    p.getExtension(),
                    p.getContext(),
                    1, p.getApp(),
                    p.getAppArgs(),
                    p.getCallerId(),
                    p.getTimeout());
      
      
    }

    @Override
    public List<BridgeResponse> getBridges(String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BridgeResponse saveBridge(BridgeDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BridgeResponse getBridge(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addChannelToBridge(String bridgeId, String channelId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EndpointResponse> endpoints() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeChannelFromBridge(String bridgeId, String channelId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteBridge(String bridgeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playMoh(String bridgeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stopMoh(String bridgeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PlaybackResponse startMediaPlayback(String bridgeId, String playbackId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getChannels() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void answerChannel(String channelId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ringChannel(String channelId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BridgeEagerLoaded getBridgeDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

  
   

  


}
