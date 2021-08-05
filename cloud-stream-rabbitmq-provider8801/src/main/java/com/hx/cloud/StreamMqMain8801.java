package com.hx.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding
public class StreamMqMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMqMain8801.class,args);
    }
}
