/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.controller;

import com.johnson3yo.ariproxy.dto.PayloadDTO;
import com.johnson3yo.ariproxy.datao.CallLog;
import com.johnson3yo.ariproxy.datao.User;
import com.johnson3yo.ariproxy.dto.BridgeDTO;
import com.johnson3yo.ariproxy.dto.BridgeResponse;
import com.johnson3yo.ariproxy.dto.Channel;
import com.johnson3yo.ariproxy.dto.EndpointResponse;
import com.johnson3yo.ariproxy.dto.PlaybackResponse;
import com.johnson3yo.ariproxy.service.ARIService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("ari-proxy")
public class ProxyController {

    @Autowired
    @Qualifier(value = "default")
    private ARIService service;

    @PostMapping("channels")
    public ResponseEntity createChannel(@RequestBody PayloadDTO payload)  {
        Channel c = (Channel) service.originate(payload);
        return new ResponseEntity<Channel>(c, HttpStatus.OK);
    }

    @PostMapping("channels/{channelId}/answer")
    public ResponseEntity createChannel(@PathVariable("channelId") String channelId)  {
        service.answerChannel(channelId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("channels")
    public ResponseEntity getChannels()  {
        List<Channel> c = service.getChannels();
        return new ResponseEntity<List<Channel>>(c, HttpStatus.OK);
    }

    @GetMapping("bridges")
    public ResponseEntity getBridges(@RequestParam(required = false, value = "type") String type)  {
        List<BridgeResponse> bridges = service.getBridges(type);
        return new ResponseEntity<List<BridgeResponse>>(bridges, HttpStatus.OK);
    }

    @GetMapping("bridges/{id}")
    public ResponseEntity getBridge(@PathVariable("id") String id)  {
        BridgeResponse bridge = service.getBridge(id);
        return new ResponseEntity<BridgeResponse>(bridge, HttpStatus.OK);
    }

    @PostMapping("bridges")
    public ResponseEntity saveBridge(@Valid @RequestBody BridgeDTO dto) {
        service.saveBridge(dto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("bridges/{bridgeId}/{channelId}")
    public ResponseEntity addChannelToBridge(
            @PathVariable("bridgeId") String bridgeId,
            @PathVariable("channelId") String channelId) {
        service.addChannelToBridge(bridgeId, channelId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("channels/{bridgeId}/{channelId}")
    public ResponseEntity removeChannelFromBridge(
            @PathVariable("bridgeId") String bridgeId,
            @PathVariable("channelId") String channelId) {
        service.removeChannelFromBridge(bridgeId, channelId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("bridges/{bridgeId}")
    public ResponseEntity deleteBridge(@PathVariable("bridgeId") String bridgeId) {
        service.deleteBridge(bridgeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("endpoints")
    public ResponseEntity getEndpoints() {
        List<EndpointResponse> endpoints = service.endpoints();
        return new ResponseEntity<List<EndpointResponse>>(endpoints, HttpStatus.OK);
    }

    @PostMapping("bridges/{bridgeId}/moh")
    public ResponseEntity playMoh(@PathVariable("bridgeId") String bridgeId
    ) {
        service.playMoh(bridgeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("bridges/{bridgeId}/moh")
    public ResponseEntity stopMoh(@PathVariable("bridgeId") String bridgeId) {
        service.stopMoh(bridgeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("bridges/{bridgeId}/play/{playbackId}")
    public ResponseEntity startPlayback(@PathVariable("bridgeId") String bridgeId,
            @PathVariable("playbackId") String playbackId) {
        PlaybackResponse pb = service.startMediaPlayback(bridgeId, playbackId);
        return new ResponseEntity<PlaybackResponse>(pb, HttpStatus.NO_CONTENT);

    }

    @GetMapping("bridges/{bridgeId}/playmedia")
    public ResponseEntity playMediaInBridge(@PathVariable("bridgeId") String bridgeId,
            @RequestParam(value = "text", required = false) String text) {
        PlaybackResponse pb = service.playMediaInBridge(bridgeId, text);
        return new ResponseEntity<PlaybackResponse>(pb, HttpStatus.NO_CONTENT);
    }

    @PostMapping("calls")
    public ResponseEntity saveCall(@RequestBody CallLog call) {
        CallLog calllog = service.saveCall(call);
        return new ResponseEntity<CallLog>(calllog, HttpStatus.CREATED);
    }

    @PutMapping("calls")
    public ResponseEntity updateCall(@RequestBody CallLog call) {
        CallLog calllog = service.updateCall(call);
        return new ResponseEntity<CallLog>(calllog, HttpStatus.OK);
    }

    @GetMapping("calls/{pageNo}")
    public ResponseEntity getCalls(
            @PathVariable("pageNo") Integer pageNo,
            @RequestParam(value = "limit", required = false, defaultValue = "100") Integer limit) {
        List<CallLog> logs = service.getCalls(pageNo, limit);
        return new ResponseEntity<List<CallLog>>(logs, HttpStatus.OK);
    }

    @PostMapping("users")
    public ResponseEntity saveUser(@RequestBody User user) {
        User u = service.saveUser(user);
        return new ResponseEntity<User>(u, HttpStatus.CREATED);
    }

    @PutMapping("users/{userId}")
    public ResponseEntity updateUser(@PathVariable("userId") Integer userId, @RequestBody User user) {
        user.setId(userId);
        User u = service.updateUser(user);
        return new ResponseEntity<User>(u, HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity getUsers() {
        List<User> users = service.getUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

}
