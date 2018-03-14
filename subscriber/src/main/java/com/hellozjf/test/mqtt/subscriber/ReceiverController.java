package com.hellozjf.test.mqtt.subscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("receiver")
public class ReceiverController {

    @Autowired
    private MqttReceiveValues mqttReceiveValues;

    @GetMapping("list")
    public Map<String, Integer> list() {
        return mqttReceiveValues.getMap();
    }
}
