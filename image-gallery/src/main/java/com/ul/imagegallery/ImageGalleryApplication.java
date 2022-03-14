package com.ul.imagegallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ImageGalleryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageGalleryApplication.class, args);
    }

}
