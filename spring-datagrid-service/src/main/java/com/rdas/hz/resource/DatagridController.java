package com.rdas.hz.resource;

import com.hazelcast.core.HazelcastInstance;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/hazelcast")
public class DatagridController {

    private final HazelcastInstance hazelcastInstance;


    public DatagridController(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    //curl -X POST http://localhost:8080/hazelcast/write/key/value
    @PostMapping("/write/{key}/{value}")
    public String write(@PathVariable("key") String key, @PathVariable("value") String value) {
        log.info("Storing key: {} with value: {}", key, value);
        Map<String, String> map = hazelcastInstance.getMap("memory");
        map.putIfAbsent(key, value);
        return "Key and value stored";
    }

    //curl http://localhost:8080/hazelcast/read/key
    @GetMapping("/read/{key}")
    public String read(@PathVariable("key") String key) {
        log.info("Reading stored value with key: {}", key);
        Map<String, String> map = hazelcastInstance.getMap("memory");
        return map.get(key);
    }

    @GetMapping(value = "/read-all-data")
    public Map<String, String> readAllDataFromHazelcast() {
        Map<String, String> hazelcastMap = hazelcastInstance.getMap("memory");
        return hazelcastMap;
    }
}
