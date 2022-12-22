package ro.fasttrackit.course5.homework.country;

import ro.fasttrackit.course5.homework.domain.Country;

import java.util.List;
import java.util.Set;

import static java.lang.Long.parseLong;

public interface CountryProvider {
    List<Country> readCountries();

    default Country mapToCountry(String line) {
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        String capital = tokens[1];
        Long population = parseLong(tokens[2]);
        Long area = parseLong(tokens[3]);
        String continent = tokens[4];
        Set<String> neighbours = mapNeighbours(tokens);
        return Country.builder()
                .name(name)
                .capital(capital)
                .population(population)
                .area(area)
                .continent(continent)
                .neighbours(neighbours)
                .build();
    }

    private Set<String> mapNeighbours(String[] tokens) {
        return tokens.length == 6 ? parseNeighbours(tokens[5]) : Set.of();
    }

    private Set<String> parseNeighbours(String neighbours) {
        String[] neighboursTokens = neighbours.split("~");
        return Set.of(neighboursTokens);
    }
}
