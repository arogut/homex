package com.arogut.shas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class DummyEdgeApplication implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
    }

    public static void main(String[] args) {
        SpringApplication.run(DummyEdgeApplication.class, args);
    }

}
