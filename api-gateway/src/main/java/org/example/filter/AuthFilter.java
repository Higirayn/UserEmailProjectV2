package org.example.filter;

import org.apache.http.HttpHeaders;
import org.example.util.JwtService;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.stereotype.Component;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Service;


@Service
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config>{

    public AuthFilter(RouteValidator validator, JwtService jwtService) {
        super(Config.class);
        this.validator = validator;
        this.jwtService = jwtService;
    }

    private final RouteValidator validator;
    private final JwtService jwtService;


    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) ->
        {
            if(validator.isSecured.test(exchange.getRequest())) {
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing auth header");
                }
                String auth = exchange.getRequest().getHeaders().getFirst(org.springframework.http.HttpHeaders.AUTHORIZATION);
                if(auth != null && !auth.isEmpty()) {
                    auth = auth.substring("Bearer ".length());  //Bearer TOKEN
                }
                try {
                    jwtService.validateToken(auth);
                } catch (Exception e) {
                    throw new RuntimeException("Unauthorized");
                }
            }

            return chain.filter(exchange);
        });
    }

    public static class Config {}

}


//localhost:8087/api/
pirivet sasa