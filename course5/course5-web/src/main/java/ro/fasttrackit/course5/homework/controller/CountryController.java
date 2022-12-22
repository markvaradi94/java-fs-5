package ro.fasttrackit.course5.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import ro.fasttrackit.course5.homework.domain.Country;
import ro.fasttrackit.course5.homework.service.CountryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService service;

    @GetMapping
    List<Country> allCountries() {
        return service.getAllCountries();
    }

    @RequestScope
    @GetMapping("mine")
    Country myCountry(@RequestHeader(value = "X-Country", required = false) String countryName) {
        return service.getMyCountry(countryName);
    }

    @GetMapping("mine-scoped")
    Country myCountryScoped() {
        return service.getMyScopedCountry()
                .orElseThrow(() -> new RuntimeException("My country doesn't exist."));
    }

    @GetMapping("names")
    List<String> countryNames() {
        return service.getCountryNames();
    }

    @GetMapping("{id}/capital")
    String countryCapital(@PathVariable String id) {
        return service.getCapital(id);
    }

    @GetMapping("{id}/population")
    Long countryPopulation(@PathVariable String id) {
        return service.getPopulation(id);
    }

    @GetMapping("{id}/neighbours")
    List<String> countryNeighbours(@PathVariable String id) {
        return service.getNeighbours(id);
    }

    @GetMapping("population")
    Map<String, Long> countryToPopulation() {
        return service.mapCountriesToPopulation();
    }
}
