package com.jake.cdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CdcStreamKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CdcStreamKafkaApplication.class, args);
    }

}
