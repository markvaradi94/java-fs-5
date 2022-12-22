package ro.fasttrackit.course5.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ro.fasttrackit.course5.homework.config.CountriesConfig;

@SpringBootApplication
@EnableConfigurationProperties(CountriesConfig.class)
public class Course5WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(Course5WebApplication.class, args);
    }

}
