package ro.fasttrackit.course5.homework.country;

import lombok.Builder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ro.fasttrackit.course5.homework.domain.Country;

import java.util.List;
import java.util.Set;

@Builder
@Component
@Profile("memory")
public record InMemoryCountryProvider(

) implements CountryProvider {
    @Override
    public List<Country> readCountries() {
        return List.of(
                Country.builder()
                        .name("Dumbfukistan")
                        .capital("Washington")
                        .population(120_000_000L)
                        .area(125_000_235L)
                        .continent("Americas")
                        .neighbours(Set.of("MEX", "CAN"))
                        .build(),
                Country.builder()
                        .name("Abonysia")
                        .capital("Negropolis")
                        .population(2_000_000L)
                        .area(125_000L)
                        .continent("Africa")
                        .neighbours(Set.of("EGY", "NIG", "CON"))
                        .build(),
                Country.builder()
                        .name("Water World")
                        .capital("Sink")
                        .population(5_000L)
                        .area(1235L)
                        .continent("Oceania")
                        .neighbours(Set.of())
                        .build(),
                Country.builder()
                        .name("Empire of Japan")
                        .capital("Hiroshima")
                        .population(12_000_000L)
                        .area(12_235L)
                        .continent("Asia")
                        .neighbours(Set.of("CHN", "IND", "KOR"))
                        .build(),
                Country.builder()
                        .name("Mayan Kingdom")
                        .capital("Machu Pichu")
                        .population(7_000_000L)
                        .area(12_023L)
                        .continent("Americas")
                        .neighbours(Set.of("ARG", "BRA", "CHL"))
                        .build()
        );
    }
}
