/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnson3yo.ariproxy.service;

import ch.loway.oss.ari4java.tools.RestException;
import com.johnson3yo.ariproxy.dto.BridgeDTO;
import com.johnson3yo.ariproxy.dto.BridgeEagerLoaded;
import com.johnson3yo.ariproxy.dto.BridgeResponse;
import com.johnson3yo.ariproxy.dto.Channel;
import com.johnson3yo.ariproxy.dto.EndpointResponse;
import com.johnson3yo.ariproxy.dto.PayloadDTO;
import com.johnson3yo.ariproxy.dto.PlaybackResponse;
import java.util.List;

/**
 *
 * @author johnson3yo
 */
public interface ARIService<T> {

    T originate(PayloadDTO p) throws RestException;

    List<BridgeResponse> getBridges(String type);

    BridgeResponse saveBridge(BridgeDTO dto);

    BridgeResponse getBridge(String id);

    void addChannelToBridge(String bridgeId, String channelId);

    List<EndpointResponse> endpoints();

    void removeChannelFromBridge(String bridgeId, String channelId);

    void deleteBridge(String bridgeId);

    void playMoh(String bridgeId);

    void stopMoh(String bridgeId);

    PlaybackResponse startMediaPlayback(String bridgeId, String playbackId);

    List<Channel> getChannels();

    void answerChannel(String channelId);

    void ringChannel(String channelId);

    BridgeEagerLoaded getBridgeDetails();

}
