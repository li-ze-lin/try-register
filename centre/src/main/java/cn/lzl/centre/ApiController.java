package cn.lzl.centre;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequestMapping
@RestController
public class ApiController {

    private final Map<String, List<ServiceData>> SERVICES = new ConcurrentHashMap<>();

    @PostMapping("/register")
    public synchronized String register(@RequestBody ServiceData serviceData) {

        List<ServiceData> serviceList = SERVICES.get(serviceData.getName());
        if (serviceList == null || serviceList.isEmpty()) {
            if (serviceList == null) {
                serviceList = new LinkedList<>();
            }

            serviceData.up();
            serviceList.add(serviceData);
            SERVICES.put(serviceData.getName(), serviceList);
            return "success";
        }

        int size = serviceList.size();
        for (int i = 0; i < size; i++) {
            ServiceData data = serviceList.get(i);
            if (data.equals(serviceData)) {
                serviceData.up();
                serviceList.add(i, serviceData);
                break;
            }
        }

        return "success";
    }

    @GetMapping
    public List<ServiceData> services() {
        List<ServiceData> serviceDataList = new LinkedList<>();
        SERVICES.forEach((k, v) -> serviceDataList.addAll(v));
        return serviceDataList;
    }

    @GetMapping("/{service}")
    public List<ServiceData> services(@PathVariable("service") String service) {
        List<ServiceData> serviceDataList = SERVICES.get(service);
        return serviceDataList == null ? Collections.emptyList() : serviceDataList;
    }
}
