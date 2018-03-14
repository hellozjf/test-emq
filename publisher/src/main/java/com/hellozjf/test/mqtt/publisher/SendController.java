package com.hellozjf.test.mqtt.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/send")
public class SendController {
    @Autowired
    private MqttSendValues mqttSendValues;

    @RequestMapping("/list")
    public Map<String, MqttSendStruct> list() {
        return mqttSendValues.getSendStructMap();
    }
}
