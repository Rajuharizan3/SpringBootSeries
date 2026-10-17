package com.onlyrj.DemoApplication;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("payment-property")
public class PaymentProperties {

    private String type;
    private int retryCount;
    private boolean enabled;
    private int timeout;

    public boolean isEnabled() {
        return enabled;
    }

    public String getType() {
        return type;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public void setEnabled(boolean enable) {
        enabled = enable;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
