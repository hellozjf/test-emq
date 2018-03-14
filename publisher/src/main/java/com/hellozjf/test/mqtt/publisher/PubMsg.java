package com.hellozjf.test.mqtt.publisher;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Data
@Slf4j
public class PubMsg {

    @Autowired
    private MqttConfiguration mqttConfiguration;

    private MqttClient mqttClient;

    public MqttClient connect(String clientId) throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName(mqttConfiguration.getUsername());
        connOpts.setPassword(mqttConfiguration.getPassword().toCharArray());
        connOpts.setConnectionTimeout(10);
        connOpts.setKeepAliveInterval(20);

        mqttClient = new MqttClient(mqttConfiguration.getAddress(), clientId, persistence);
        mqttClient.setCallback(new PushCallback(clientId));
        mqttClient.connect(connOpts);
        return mqttClient;
    }

    public void send(String topic, String message) throws MqttException {
        log.debug("clientId={}, topic={}, message={}", getMqttClient().getClientId(), topic, message);
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(mqttConfiguration.getQos());
        mqttMessage.setRetained(false);
        mqttClient.publish(topic, mqttMessage);
    }
}
