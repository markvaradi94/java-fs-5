package ro.fasttrackit.course5.homework.domain;

import lombok.Builder;

@Builder
public record CountryRequest(
        Long minPopulation,
        String include,
        String exclude
) {
}
