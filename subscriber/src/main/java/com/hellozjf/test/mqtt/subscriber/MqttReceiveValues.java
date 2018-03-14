package com.hellozjf.test.mqtt.subscriber;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class MqttReceiveValues {
    private Map<String, Integer> map = new HashMap<>();

    public void addToMap(String topic) {
        if (map.get(topic) == null) {
            map.put(topic, 1);
        } else {
            map.put(topic, map.get(topic));
        }
    }
}
