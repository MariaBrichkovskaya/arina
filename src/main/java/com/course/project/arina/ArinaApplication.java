package com.course.project.arina;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class ArinaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArinaApplication.class, args);
    }


}
