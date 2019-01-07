package org.apache.skywalking.netty.http.scenario;

import org.apache.skywalking.netty.http.scenario.config.AppProperties;
import org.apache.skywalking.netty.http.scenario.payload.GithubRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Jian.tan
 */

@Service
public class GithubClient {
    private static final String GITHUB_V3_MIME_TYPE = "application/vnd.github.v3+json";
    private static final String GITHUB_API_BASE_URL = "https://api.github.com";
    private static final String USER_AGENT = "Spring 5 WebClient";
    private static final Logger logger = LoggerFactory.getLogger(GithubClient.class);

    private final WebClient webClient;

    @Autowired
    public GithubClient(AppProperties appProperties) {
        this.webClient = WebClient.builder()
                .baseUrl(GITHUB_API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, GITHUB_V3_MIME_TYPE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .filter(ExchangeFilterFunctions
                        .basicAuthentication(appProperties.getGithub().getUsername(),
                                appProperties.getGithub().getToken()))
                .filter(logRequest())
                .build();
    }


    public Flux<GithubRepo> listGithubRepositories() {
        return webClient.get()
                .uri("/user/repos?sort={sortField}&direction={sortDirection}",
                        "updated", "desc")
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(GithubRepo.class));
    }


    public Mono<GithubRepo> getGithubRepository(String owner, String repo) {
        return webClient.get()
                .uri("/repos/{owner}/{repo}", owner, repo)
                .retrieve()
                .bodyToMono(GithubRepo.class);
    }

    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
            return next.exchange(clientRequest);
        };
    }
}
