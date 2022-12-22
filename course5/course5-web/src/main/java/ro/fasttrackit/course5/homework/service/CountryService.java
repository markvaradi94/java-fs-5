package ro.fasttrackit.course5.homework.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.course5.homework.country.CountryProvider;
import ro.fasttrackit.course5.homework.domain.Country;
import ro.fasttrackit.course5.homework.domain.CountryRequest;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryProvider countryProvider;
    private final CountryContext countryContext;
    private List<Country> countries;

    @PostConstruct
    void setupCountries() {
        this.countries = ofNullable(countryProvider.readCountries())
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
    }

    public List<Country> getAllCountries() {
        return unmodifiableList(countries);
    }

    public List<String> getCountryNames() {
        return countries.stream()
                .map(Country::name)
                .toList();
    }

    public String getCapital(String id) {
        var country = getOrThrow(id);
        return country.capital();
    }

    public Long getPopulation(String id) {
        var country = getOrThrow(id);
        return country.population();
    }

    public List<String> getNeighbours(String id) {
        var country = getOrThrow(id);
        return ofNullable(country.neighbours())
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
    }

    public List<Country> getCountriesFromContinent(String continentName, CountryRequest request) {
        return request == null ? findCountriesByContinent(continentName) : findByRequestFilters(continentName, request);
    }

    private List<Country> findByRequestFilters(String continentName, CountryRequest request) {
        return request.minPopulation() == null
                ? findCountriesByNeighbours(continentName, request.include(), request.exclude())
                : findCountriesByMinPopulation(continentName, request.minPopulation());
    }

    private List<Country> findCountriesByNeighbours(String continentName, String include, String exclude) {
        return findCountriesByContinent(continentName).stream()
                .filter(country -> filterByNeighbours(country, include, exclude))
                .toList();
    }

    private boolean filterByNeighbours(Country country, String include, String exclude) {
        return country.neighbours().contains(include.toUpperCase()) && !country.neighbours().contains(exclude.toUpperCase());
    }

    private List<Country> findCountriesByMinPopulation(String continentName, Long population) {
        return findCountriesByContinent(continentName).stream()
                .filter(country -> country.population() >= population)
                .toList();
    }

    private List<Country> findCountriesByContinent(String continentName) {
        return countries.stream()
                .filter(country -> continentName.equalsIgnoreCase(country.continent()))
                .distinct()
                .toList();
    }

    private Country getOrThrow(String id) {
        return countries.stream()
                .filter(country -> id.equalsIgnoreCase(country.id()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find country with id: " + id));
    }

    public Map<String, Long> mapCountriesToPopulation() {
        return countries.stream()
                .collect(toMap(Country::name, Country::population));
    }

    public Map<String, List<Country>> mapCountriesToContinents() {
        return countries.stream()
                .collect(Collectors.groupingBy(
                        Country::continent,
                        TreeMap::new,
                        toList()
                ));
    }

    public Country getMyCountry(String countryName) {
        return countries.stream()
                .filter(country -> countryName.equalsIgnoreCase(country.name()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find country with name " + countryName));
    }

    public Optional<Country> getMyScopedCountry() {
        return countries.stream()
                .filter(country -> countryContext.getMyCountryName().equalsIgnoreCase(country.name()))
                .findFirst();
    }
}
