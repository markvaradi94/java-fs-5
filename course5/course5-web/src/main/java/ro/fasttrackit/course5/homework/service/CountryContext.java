package ro.fasttrackit.course5.homework.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import ro.fasttrackit.course5.homework.exceptions.HeaderNotFoundException;

import static java.util.Optional.ofNullable;

@Data
@Component
@RequestScope
public class CountryContext {
    private final String myCountryName;

    public CountryContext(HttpServletRequest request) {
        this.myCountryName = ofNullable(request.getHeader("X-Country"))
                .orElseThrow(() -> new HeaderNotFoundException("No X-Country header found"));
    }
}
