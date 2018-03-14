package com.hellozjf.test.mqtt.publisher;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Data
public class MqttSendValues {
    private Map<String, MqttSendStruct> sendStructMap = new ConcurrentHashMap<>();

    public void addToMap(String topic, String message) {
        MqttSendStruct mqttSendStruct = sendStructMap.get(topic);
        if (mqttSendStruct == null) {
            mqttSendStruct = new MqttSendStruct();
            mqttSendStruct.setTopic(topic);
            mqttSendStruct.setSendCount(1);
            mqttSendStruct.setSendBytes(message.length());
        } else {
            mqttSendStruct.setSendCount(mqttSendStruct.getSendCount() + 1);
            mqttSendStruct.setSendBytes(mqttSendStruct.getSendBytes() + message.length());
        }
        sendStructMap.put(topic, mqttSendStruct);
    }
}
