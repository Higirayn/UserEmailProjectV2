package org.example.filter;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of("/auth/register", "/auth/validate", "/auth/token");
    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest ->
        openApiEndpoints
                .stream()
                .noneMatch(
                        uri -> serverHttpRequest.getURI().toString().equals(uri)
                );
}
