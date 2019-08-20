package io.github.antolius.scg.examples.headers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder,
			@Value("${backend.url}") String backendUrl) {
		return builder.routes()
				.route(r -> r.path("/foo/bars")
						.filters(f -> f.setRequestHeader(HttpHeaders.CONNECTION, "keep-alive"))
						.uri(backendUrl))
				.build();
	}
}
