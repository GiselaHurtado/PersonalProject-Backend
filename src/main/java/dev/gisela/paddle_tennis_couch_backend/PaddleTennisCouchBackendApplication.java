package dev.gisela.paddle_tennis_couch_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan(basePackages = "dev.gisela.paddle_tennis_couch_backend")
public class PaddleTennisCouchBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaddleTennisCouchBackendApplication.class, args);
    }
}
