package com.in28minutes.microservices.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    /**
     * get the path that matches the pattern and redirect it to load balancer
     * @param builder
     * @return the redirected url
     */
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){

        // gets the path from currency conversion  and redirect it to currency conversion feign the last route

        return builder.routes()
                .route(route ->  route.path("/get")
                        .filters(f -> f.addRequestHeader("MyHeader", "MyUri")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange"))
                .route(p -> p.path("/currency-conversion/**").uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-feign/**").uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-new/**").filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)","/currency-conversion-feign/${segment}")).uri("lb://currency-conversion"))
                .build();

    }
}
