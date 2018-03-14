package com.hellozjf.test.mqtt.publisher;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class SendMqttConfiguration {
    @Value("${sendMqtt.threadNum}")
    private Integer threadNum;
    @Value("${sendMqtt.sleepMsTime}")
    private Long sleepMsTime;
    @Value("${sendMqtt.sendSize}")
    private Integer sendSize;
}
