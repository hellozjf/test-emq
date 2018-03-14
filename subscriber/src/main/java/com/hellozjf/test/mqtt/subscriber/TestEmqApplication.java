package com.hellozjf.test.mqtt.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@Slf4j
@EnableAsync
public class TestEmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestEmqApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(PubMsg pubMsg) {
	    return args -> {
	        // 不要强制关闭线程，让线程自然退出
//	        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//	            shutdownConfiguration.setShutdown(true);
//            }));
	        // 启动接收线程
            pubMsg.connect("receiver" + RandomStringUtils.randomAlphanumeric(10));
            pubMsg.subscribe("topic/#");
        };
    }
}
