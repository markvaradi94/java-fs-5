package ro.fasttrackit.course5.homework.country;

import lombok.Builder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ro.fasttrackit.course5.homework.config.CountriesConfig;
import ro.fasttrackit.course5.homework.domain.Country;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Builder(toBuilder = true)
@Component
@Profile("file")
public record FileCountryProvider(
        CountriesConfig config
) implements CountryProvider {
    @Override
    public List<Country> readCountries() {
        try (BufferedReader reader = new BufferedReader(new FileReader(config.location()))) {
            return reader.lines()
                    .map(this::mapToCountry)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
