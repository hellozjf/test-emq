package com.hellozjf.test.mqtt.publisher;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ShutdownConfiguration {
    private boolean shutdown = false;
}
