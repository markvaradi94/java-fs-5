package ro.fasttrackit.course5.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.course5.homework.domain.Country;
import ro.fasttrackit.course5.homework.domain.CountryRequest;
import ro.fasttrackit.course5.homework.service.CountryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("continents")
@RequiredArgsConstructor
public class ContinentController {
    private final CountryService service;

    @GetMapping("{continentName}/countries")
    public List<Country> countriesInContinent(@PathVariable String continentName, CountryRequest request) {
        return service.getCountriesFromContinent(continentName, request);
    }

    @GetMapping("countries")
    public Map<String, List<Country>> mapContinentsToCountries() {
        return service.mapCountriesToContinents();
    }
}
