/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.johnson3yo.ariproxy.datao.CallLog;
import com.johnson3yo.ariproxy.datao.User;
import com.johnson3yo.ariproxy.datao.UserActivity;
import com.johnson3yo.ariproxy.dto.BridgeDTO;
import com.johnson3yo.ariproxy.dto.BridgeEagerLoaded;
import com.johnson3yo.ariproxy.dto.BridgeResponse;
import com.johnson3yo.ariproxy.dto.Channel;
import com.johnson3yo.ariproxy.dto.EndpointResponse;
import com.johnson3yo.ariproxy.dto.PayloadDTO;
import com.johnson3yo.ariproxy.dto.PlaybackResponse;
import com.johnson3yo.ariproxy.repository.CallLogRepository;
import com.johnson3yo.ariproxy.repository.UserActivityRepository;
import com.johnson3yo.ariproxy.repository.UserRepository;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author johnson3yo
 */
@Service(value = "default")
public class DefaultAPIService implements ARIService<Channel> {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${ari.host}")
    private String host;
    @Value("${ari.username}")
    private String username;
    @Value("${ari.password}")
    private String password;
    @Value("${base.url}")
    private String baseURL;
    @Value("${media.stream.synthesize.stream}")
    private String media;
    @Autowired
    private CallLogRepository callRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserActivityRepository userActivityRepo;

    private static HttpHeaders headers;

    @Autowired
    private GoogleService gservice;

    @PostConstruct
    public void geAuthHeaders() {
        headers = new HttpHeaders() {
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.encodeBase64(
                        auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }

    @Override
    public Channel originate(PayloadDTO p) {

        Channel readValue = null;
        String endpoint = p.getEndpoint();
        String app = p.getApp();
        String extension = p.getExtension();
        Integer timeout = p.getTimeout();
        try {
            HttpEntity entity = new HttpEntity(p, headers);
            ResponseEntity<String> exchange = restTemplate.
                    exchange(baseURL.concat("/channels?endpoint={endpoint}&app={app}&extension={extension}"),
                            HttpMethod.POST, entity, String.class, endpoint, app, extension);
            ObjectMapper mapper = new ObjectMapper();
            readValue = mapper.readValue(exchange.getBody(), Channel.class);
        } catch (IOException ex) {
            Logger.getLogger(DefaultAPIService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return readValue;
    }

    @Override
    public List<BridgeResponse> getBridges(String type) {
        List<BridgeResponse> readValue = null;
        try {
            HttpEntity entity = new HttpEntity("parameters", headers);
            ResponseEntity<String> exchange = restTemplate.
                    exchange(baseURL.concat("/bridges"), HttpMethod.GET, entity, String.class);
            ObjectMapper mapper = new ObjectMapper();

            if (type.equals("undefined")) {
                readValue = readValue = mapper.readValue(exchange.getBody(), new TypeReference<List<BridgeResponse>>() {
                });
            }

            if (Optional.ofNullable(type).isPresent()) {
                readValue = mapper.readValue(exchange.getBody(), new TypeReference<List<BridgeResponse>>() {
                });
                readValue = readValue.stream().
                        filter(f -> f.getBridgeType().equals(type))
                        .collect(Collectors.toList());
            }

           
            for (BridgeResponse br : readValue) {
                 List<BridgeResponse.Node> nodes = new ArrayList();
             BridgeResponse.Node node = new BridgeResponse.Node("0803", "participants");
             nodes.add(node);  
               
                 for (String id : br.getChannels()) {
                      List<BridgeResponse.Node.Children> children = new ArrayList();            
                    BridgeResponse.Node.Children child = new BridgeResponse.Node.Children();
                    child.setId(id);
                    child.setName("demo".concat(id));
                    children.add(child);
                    node.setChildren(children);                    
                }                         
                 br.setNodes(nodes);
            }
            return readValue;
        } catch (IOException ex) {
            Logger.getLogger(DefaultAPIService.class.getName()).log(Level.SEVERE, null, ex);
            return readValue;
        }
    }

    @Override
    public BridgeResponse saveBridge(BridgeDTO dto) {
        BridgeResponse readValue = null;
        try {
            HttpEntity entity = new HttpEntity(dto, headers);
            ResponseEntity<String> exchange = restTemplate.
                    exchange(baseURL.concat("/bridges"), HttpMethod.POST, entity, String.class);
            ObjectMapper mapper = new ObjectMapper();
            readValue = mapper.readValue(exchange.getBody(), BridgeResponse.class);
        } catch (IOException ex) {
            Logger.getLogger(DefaultAPIService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return readValue;
    }

    @Override
    public BridgeResponse getBridge(String id) {
        BridgeResponse readValue = null;
        try {
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<String> exchange = restTemplate.
                    exchange(baseURL.concat("/bridges/").concat(id), HttpMethod.GET, entity, String.class);
            ObjectMapper mapper = new ObjectMapper();
            readValue = mapper.readValue(exchange.getBody(), BridgeResponse.class);
            List<BridgeResponse.Node> nodes = new ArrayList();

            BridgeResponse.Node node = new BridgeResponse.Node();
            List<BridgeResponse.Node.Children> children = new ArrayList();
            for (String idp : readValue.getChannels()) {
                BridgeResponse.Node.Children child = new BridgeResponse.Node.Children();
                child.setId(idp);
                child.setName("demo".concat(idp));
                children.add(child);
            }
            node.setChildren(children);
            nodes.add(node);
            readValue.setNodes(nodes);

        } catch (IOException ex) {
            Logger.getLogger(DefaultAPIService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return readValue;
    }

    @Override
    public void addChannelToBridge(String bridgeId, String channelId) {
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> exchange = restTemplate.
                exchange(baseURL.
                        concat("/bridges/{bridgeId}/addChannel?channel={channelId}"),
                        HttpMethod.POST, entity, String.class, bridgeId, channelId);

    }

    @Override
    public List<EndpointResponse> endpoints() {
        List<EndpointResponse> readValue = null;
        try {
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<String> exchange = restTemplate.
                    exchange(baseURL.concat("/endpoints"), HttpMethod.GET, entity, String.class);
            ObjectMapper mapper = new ObjectMapper();
            readValue = mapper.readValue(exchange.getBody(), new TypeReference<List<EndpointResponse>>() {

            });

            for (EndpointResponse er : readValue) {
                EndpointResponse.CurrentChannel cc = new EndpointResponse().new CurrentChannel();
                cc.setId(Arrays.asList(er.getChannelIds()).isEmpty() ? null : er.getChannelIds()[0]);
                cc.setName(Arrays.asList(er.getChannelIds()).isEmpty() ? null : "demo");
                er.setCurrentChannel(cc);
            }
        } catch (IOException ex) {
            Logger.getLogger(DefaultAPIService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return readValue;
    }

    @Override
    public void removeChannelFromBridge(String bridgeId, String channelId) {
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> exchange = restTemplate.
                exchange(baseURL.
                        concat("/bridges/{bridgeId}/removeChannel?channel={channelId}"),
                        HttpMethod.POST, entity, String.class, bridgeId, channelId);
    }

    @Override
    public void deleteBridge(String bridgeId) {
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> exchange = restTemplate.
                exchange(baseURL.
                        concat("/bridges/{bridgeId}"),
                        HttpMethod.DELETE, entity, String.class, bridgeId);
    }

    @Override
    public void playMoh(String bridgeId) {
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> exchange = restTemplate.
                exchange(baseURL.
                        concat("/bridges/{bridgeId}/moh"),
                        HttpMethod.POST, entity, String.class, bridgeId);
    }

    @Override
    public void stopMoh(String bridgeId) {
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> exchange = restTemplate.
                exchange(baseURL.
                        concat("/bridges/{bridgeId}/moh"),
                        HttpMethod.DELETE, entity, String.class, bridgeId);
    }

    @Override
    public PlaybackResponse startMediaPlayback(String bridgeId, String playbackId) {
        try {
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<String> exchange = restTemplate.
                    exchange(baseURL.
                            concat("/bridges/{bridgeId}/play/{playbackId}"),
                            HttpMethod.POST, entity, String.class, bridgeId, playbackId);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(exchange.getBody(), PlaybackResponse.class);
        } catch (IOException ex) {
            Logger.getLogger(DefaultAPIService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public List<Channel> getChannels() {
        List<Channel> channels = null;
        try {
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<String> exchange = restTemplate.
                    exchange(baseURL.
                            concat("/channels"),
                            HttpMethod.GET, entity, String.class);
            ObjectMapper mapper = new ObjectMapper();
            channels = mapper.readValue(exchange.getBody(), new TypeReference<List<Channel>>() {
            });
        } catch (IOException ex) {

            Logger.getLogger(DefaultAPIService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return channels;

    }

    @Override
    public void answerChannel(String channelId) {
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> exchange = restTemplate.
                exchange(baseURL.
                        concat("/channels/{channelId}/answer"),
                        HttpMethod.POST, entity, String.class, channelId);
    }

    @Override
    public void ringChannel(String channelId) {
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> exchange = restTemplate.
                exchange(baseURL.
                        concat("/channels/{channelId}/ring"),
                        HttpMethod.POST, entity, String.class, channelId);
    }

    @Override
    public BridgeEagerLoaded getBridgeDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PlaybackResponse playMediaInBridge(String bridgeId, String mediaText) {
        String mediaURL = "sound:/home/johnson3yo/Downloads/beethoven-30sec";

        try {
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<String> exchange = restTemplate.
                    exchange(baseURL.
                            concat("/bridges/{bridgeId}/play?media={mediaURL}"),
                            HttpMethod.POST, entity, String.class, bridgeId, mediaURL);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(exchange.getBody(), PlaybackResponse.class);
        } catch (IOException ex) {
            Logger.getLogger(DefaultAPIService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public CallLog saveCall(CallLog call) {
        return callRepo.save(call);
    }

    @Override
    public List<CallLog> getCalls(Integer pageNo, Integer limit) {
        Sort sort = Sort.by(
                Sort.Order.desc("id"));
        return callRepo.findAll(PageRequest.of(pageNo, limit, sort));
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return userRepo.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public UserActivity saveActivity(UserActivity userActivity) {
        return userActivityRepo.save(userActivity);
    }

    @Override
    public CallLog updateCall(CallLog call) {
        CallLog found = callRepo.findBySourceChannelId(call.getSourceChannelId());
        //  CallLog found = callRepo.findByDestinaChannelId(call.getSourceChannelId());

        CallLog updated = Optional.of(call).map(c -> {
            c.setId(found.getId());
            return c;
        }).map(e
                -> {
            String source = Optional.ofNullable(e.getSource()).orElse(found.getSource());
            e.setSource(source);
            return e;
        }).map(b -> {
            String dest = Optional.
                    ofNullable(call.getDestination()).orElse(found.getDestination());
            b.setDestination(dest);
            return b;
        }).map(f -> {
            Date endDate = Optional.ofNullable(f.getEndTime()).orElse(found.getEndTime());
            f.setEndTime(endDate);
            return f;
        }).map(q -> {
            Integer nop = Optional.ofNullable(q.getNoOfParticipants()).
                    orElse(found.getNoOfParticipants());
            q.setNoOfParticipants(nop);
            return q;
        }).map(v -> {
            Date startTime = Optional.ofNullable(v.getStartTime()).orElse(found.getStartTime());
            v.setStartTime(startTime);
            return v;
        }).map(t -> {
            Date startTime = Optional.ofNullable(t.getStartTime()).orElse(found.getStartTime());
            t.setStartTime(startTime);
            return t;
        }).map(p -> {
            String source = Optional.ofNullable(p.getSource()).orElse(found.getSource());
            p.setSource(source);
            return p;
        }).map(w -> {
            String sourceId = Optional.ofNullable(w.getSourceChannelId()).orElse(found.getSourceChannelId());
            w.setSourceChannelId(sourceId);
            return w;
        }).map(m -> {
            return m;
        }).get();
        return callRepo.save(updated);
    }

}
