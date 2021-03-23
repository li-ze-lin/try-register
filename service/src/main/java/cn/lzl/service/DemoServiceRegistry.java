package cn.lzl.service;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class DemoServiceRegistry implements ServiceRegistry<DemoRegistration> {


    @Override
    public void register(DemoRegistration registration) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:23333/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(new Data(registration).toString()))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deregister(DemoRegistration registration) {
        System.out.println("not implemented deregister");
    }

    @Override
    public void close() {
        System.out.println("not implemented close");
    }

    @Override
    public void setStatus(DemoRegistration registration, String status) {
        System.out.println("not implemented setStatus ");
    }

    @Override
    public String getStatus(DemoRegistration registration) {
        return "unknown";
    }

    @Getter
    @Setter
    private static class Data {
        private String name;
        private String ip;
        private int port;
        private String healthUrl;

        public Data(DemoRegistration registration) {
            this.name = registration.getServiceId();
            this.ip = registration.getHost();
            this.port = registration.getPort();
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
}
