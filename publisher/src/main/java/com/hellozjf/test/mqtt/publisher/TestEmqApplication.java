package com.hellozjf.test.mqtt.publisher;

import lombok.extern.slf4j.Slf4j;
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
    public Executor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(0);
        executor.setMaxPoolSize(1024);
        executor.setQueueCapacity(0);
        executor.initialize();
        return executor;
    }

    @Bean
    public CommandLineRunner commandLineRunner(SendMqttComponent sendMqttComponent, SendMqttConfiguration sendMqttConfiguration, ShutdownConfiguration shutdownConfiguration) {
	    return args -> {
	        // 不要强制关闭线程，让线程自然退出
	        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
	            shutdownConfiguration.setShutdown(true);
            }));
	        // 启动发送线程
	        for (int i = 0; i < sendMqttConfiguration.getThreadNum(); i++) {
	            String threadName = "sendMqtt" + i;
                sendMqttComponent.start(threadName, sendMqttConfiguration.getSleepMsTime(), sendMqttConfiguration.getSendSize());
            }
        };
    }
}
