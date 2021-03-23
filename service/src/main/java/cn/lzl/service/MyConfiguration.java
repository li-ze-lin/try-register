package cn.lzl.service;

import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class MyConfiguration {

    @Resource
    private ServiceRegistry serviceRegistry;
    @Resource
    private Registration registration;

    @PostConstruct
    public void initPost() {
        serviceRegistry.register(registration);
    }
}
