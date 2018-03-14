package com.hellozjf.test.mqtt.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendMqttComponent {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ShutdownConfiguration shutdownConfiguration;

    @Async
    public void start(String threadName, Long sleepMsTime, Integer sendSize) {
        Thread.currentThread().setName(threadName);
        PubMsg pubMsg = applicationContext.getBean(PubMsg.class);
        try {
            pubMsg.connect(threadName);
            String topic = "topic/" + threadName + "/" + RandomStringUtils.randomAlphanumeric(10);
            while (!shutdownConfiguration.isShutdown()) {
                Thread.sleep(sleepMsTime);
                String message = RandomStringUtils.randomAlphanumeric(sendSize);
                pubMsg.send(topic, message);
            }
        } catch (MqttException e) {
            log.error("{}", e);
        } catch (InterruptedException e) {
            log.error("{}", e);
        }
    }
}
