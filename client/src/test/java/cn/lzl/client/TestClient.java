package cn.lzl.client;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

public class TestClient {

    @Test
    void client() throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:23333/service1"))
                .build();
        HttpResponse<String> send = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(send.body());
    }
}
