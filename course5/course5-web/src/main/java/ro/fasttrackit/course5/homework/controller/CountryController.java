package ro.fasttrackit.course5.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import ro.fasttrackit.course5.homework.domain.Country;
import ro.fasttrackit.course5.homework.service.CountryService;

import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService service;

    @GetMapping
    public List<Country> allCountries() {
        return service.getAllCountries();
    }

    @RequestScope
    @GetMapping("mine")
    public Country myCountry(@RequestHeader HttpHeaders headers) {
        String countryName = fetchNameFromHeaders(headers);
        return service.getMyCountry(countryName);
    }

    @GetMapping("names")
    public List<String> countryNames() {
        return service.getCountryNames();
    }

    @GetMapping("{id}/capital")
    public String countryCapital(@PathVariable String id) {
        return service.getCapital(id);
    }

    @GetMapping("{id}/population")
    public Long countryPopulation(@PathVariable String id) {
        return service.getPopulation(id);
    }

    @GetMapping("{id}/neighbours")
    public List<String> countryNeighbours(@PathVariable String id) {
        return service.getNeighbours(id);
    }

    @GetMapping("population")
    public Map<String, Long> countryToPopulation() {
        return service.mapCountriesToPopulation();
    }

    private static String fetchNameFromHeaders(HttpHeaders headers) {
        return requireNonNull(headers.get("X-Country")).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find country name from headers"));
    }
}
