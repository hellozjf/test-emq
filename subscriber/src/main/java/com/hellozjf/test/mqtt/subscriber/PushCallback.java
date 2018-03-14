package com.hellozjf.test.mqtt.subscriber;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Slf4j
public class PushCallback implements MqttCallback {

    @Autowired
    private MqttReceiveValues mqttReceiveValues;

    private String clientId;

    public PushCallback() {
    }

    public PushCallback(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void connectionLost(Throwable cause) {
        log.info("{}-connectionLost, cause={}", clientId, cause);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.debug("{}-deliveryComplete", clientId);
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        log.debug("{}-messageArrived, topic={}, message={}", clientId, topic, message);
        mqttReceiveValues.addToMap(topic);
    }
}