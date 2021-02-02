package com.learning.trackzilla.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Implement a sample of Actuator Indicator
 */
@Component
public class BugScanJobHealthIndicator implements HealthIndicator {
    private final String message_key = "BugScanBackgroundJob";

    @Override
    public Health health() {
        if(!isRunningBugScanJob()){
            return Health.down().withDetail(message_key, "Not Available").build();
        }
        return Health.up().withDetail(message_key, "Available").build();
    }

    private Boolean isRunningBugScanJob() {
        return false;
    }
}
