package cn.lzl.client;

import com.alibaba.fastjson.JSONArray;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RequestMapping
@RestController
public class ApiController {

    @GetMapping
    public String callService() throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:23333/service1"))
                .build();
        HttpResponse<String> send = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<Services> services = JSONArray.parseArray(send.body(), Services.class);
        if (services == null || services.isEmpty()) {
            return "not service";
        }

        Services services1 = services.get(0);
        HttpResponse<String> send1 = HttpClient.newHttpClient().send(HttpRequest.newBuilder().uri(URI.create(String.format("http://%s:%s", services1.getIp(), services1.getPort()))).build(), HttpResponse.BodyHandlers.ofString());
        return send1.body();

    }

    @Getter
    @Setter
    static class Services {
        private String name;
        private String ip;
        private int port;
    }

}
