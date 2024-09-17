package dev.gisela.paddle_tennis_couch_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import dev.gisela.paddle_tennis_couch_backend.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@ComponentScan(basePackages = "dev.gisela.paddle_tennis_couch_backend")
public class PaddleTennisCouchBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaddleTennisCouchBackendApplication.class, args);
    }
}
