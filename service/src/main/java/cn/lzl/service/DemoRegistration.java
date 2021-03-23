package cn.lzl.service;

import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Map;

@Component
public class DemoRegistration implements Registration {

    @Override
    public String getServiceId() {
        return "service1";
    }

    @Override
    public String getHost() {
        return "127.0.0.1";
    }

    @Override
    public int getPort() {
        return 21111;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public URI getUri() {
        return URI.create(String.format("http://%s:%d", getHost(), getPort()));
    }

    @Override
    public Map<String, String> getMetadata() {
        return null;
    }
}
