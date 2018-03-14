package com.hellozjf.test.mqtt.publisher;

import lombok.Data;

@Data
public class MqttSendStruct {
    private String topic;
    private Integer sendCount;
    private Integer sendBytes;
}
