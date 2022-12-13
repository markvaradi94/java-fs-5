package ro.fasttrackit.course5.homework.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.annotation.Validated;


@Validated
@Primary
@Configuration
@ConfigurationProperties(prefix = "homework.countries")
public class CountriesConfig {
    @NotBlank
    private String location;

    public String location() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
