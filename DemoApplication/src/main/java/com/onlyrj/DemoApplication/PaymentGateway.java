package com.onlyrj.DemoApplication;

import com.sun.source.tree.ReturnTree;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {

//    @Value("${paymentGateway.type:Khalti}")
//    private String type;
//
//    @Value("${paymentGateway.retry-count:3}")
//    private int retryCount;

//    public PaymentGateway(@Value("${paymentGateway.type}") String type, @Value("${paymentGateway.retry-count}") int retryCount) {
//        this.type = type;
//        this.retryCount = retryCount;
//    }



    private final PaymentProperties paymentProperties;

    public PaymentGateway(PaymentProperties paymentProperties) {
        this.paymentProperties = paymentProperties;
    }

    public String getType(){
        return paymentProperties.getType();
    }

    public int getRetryCount(){
        return paymentProperties.getRetryCount();
    }

    public boolean getEnabled(){
        return paymentProperties.isEnabled();
    }

    public int getTimeOut(){
        return paymentProperties.getTimeout();
    }

    public void print(){
        System.out.println(getType());
        System.out.println(getRetryCount());
        System.out.println(getEnabled());
        System.out.println(getTimeOut());
    }

}
