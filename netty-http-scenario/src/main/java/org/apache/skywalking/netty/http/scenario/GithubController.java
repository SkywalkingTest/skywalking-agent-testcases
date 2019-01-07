package org.apache.skywalking.netty.http.scenario;

import org.apache.skywalking.netty.http.scenario.config.AppProperties;
import org.apache.skywalking.netty.http.scenario.payload.GithubRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by rajeevkumarsingh on 11/11/17.
 */
@RestController
@RequestMapping("/api")
public class GithubController {

    private GithubClient githubClient;

    private AppProperties appProperties;

    public GithubController(GithubClient githubClient, AppProperties appProperties) {
        this.githubClient = githubClient;
        this.appProperties = appProperties;
    }

    private static final Logger logger = LoggerFactory.getLogger(GithubController.class);

    @GetMapping("/repos")
    public Flux<GithubRepo> listGithubRepositories() {
        return githubClient.listGithubRepositories();
    }

    @GetMapping("/repos/{repo}")
    public Mono<GithubRepo> getGithubRepository(@PathVariable String repo) {
        return githubClient.getGithubRepository(appProperties.getGithub().getUsername(), repo);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleWebClientResponseException(WebClientResponseException ex) {
        logger.error("Error from WebClient - Status {}, Body {}", ex.getRawStatusCode(),
                ex.getResponseBodyAsString(), ex);
        return ResponseEntity.status(ex.getRawStatusCode()).body(ex.getResponseBodyAsString());
    }
}

