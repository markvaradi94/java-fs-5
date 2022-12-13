package ro.fasttrackit.course5.homework.domain;

import lombok.Builder;

import java.util.Set;

import static java.util.UUID.randomUUID;

@Builder(toBuilder = true)
public record Country(
        String id,
        String name,
        String capital,
        Long population,
        Long area,
        String continent,
        Set<String> neighbours
) {
    public Country {
        id = randomUUID().toString();
    }
}
