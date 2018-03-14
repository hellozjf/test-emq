package com.hellozjf.test.mqtt.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SendMqttSchedule {

    private List<PubMsg> pubMsgs = new ArrayList<>();
    private Integer maxSendCount = 1;

    public SendMqttSchedule(ApplicationContext applicationContext) {
        for (int i = 0; i < maxSendCount; i++) {
            PubMsg pubMsg = applicationContext.getBean(PubMsg.class);
            try {
                pubMsg.connect("SendMqttSchedule" + i);
                pubMsgs.add(pubMsg);
            } catch (MqttException e) {
                log.error("{}", e);
            }
        }
    }

//    @Scheduled(fixedDelay = 1000)
//    public void sendMqtt() {
//        for (PubMsg pubMsg : pubMsgs) {
//            try {
//                pubMsg.send("topic/" + pubMsg.getMqttClient().getClientId(), RandomStringUtils.randomAscii(1024));
//            } catch (MqttException e) {
//                log.error("{}", e);
//            }
//        }
//    }
}
