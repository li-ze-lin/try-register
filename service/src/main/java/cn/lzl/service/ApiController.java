package cn.lzl.service;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequestMapping
@RestController
public class ApiController {

    @GetMapping
    public String services() {
        return "service1";
    }

}
