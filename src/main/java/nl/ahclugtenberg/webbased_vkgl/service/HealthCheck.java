package nl.ahclugtenberg.webbased_vkgl.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * A custom health check class. No real health checks are implemented. For demonstration purposes only.
 * A random boolean determines whether the health check returns up or down.
 */
@Component
public class HealthCheck implements HealthIndicator {

    /**
     * A custom health check.
     * @return up or down with error code.
     */
    @Override
    public Health health() {
        String errorCode = check();
        if (!errorCode.equals("UP")) {
            return Health.down()
                    .withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }

    /**
     * A custom check health method. Logic for actual health check should be provided here.
     * @return a String with UP if health is good, a String with error message is health is no good.
     */
    private String check() {
        boolean randomBoolean = randomBooleanCustomErrorMessage();
        if (!randomBoolean) {
            return "1: A custom error message, for demonstration purposes.";
        } else {
            return "UP";
        }
    }

    /**
     * A method to retrieve a random boolean (true/false)
     * @return a boolean
     */
    private boolean randomBooleanCustomErrorMessage() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
