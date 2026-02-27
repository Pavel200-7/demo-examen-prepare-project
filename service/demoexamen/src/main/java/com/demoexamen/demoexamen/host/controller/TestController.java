package com.demoexamen.demoexamen.host.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test() {
        return "работает";
    }

    @GetMapping("/uri")
    public String testUri() {
        WebClient client = WebClient.create();


        return "работает";
    }
}
