package com.ms.gamehouseapigateway.routing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class RoutesConfig {

    @Value("${suggestionMS.url}")
    private String suggestionMsURL;

    @Value("${suggestionMS.predicate}")
    private String suggestionMsPredicate;

    @Value("${userMS.url}")
    private String userMsURL;

    @Value("${userMS.auth.predicate}")
    private String userAuthPredicate;

    @Value("${userMS.operations.predicate}")
    private String userOpPredicate;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // user - Auth
                .route("register_user", r -> r.path("/user_ms/register_user")
                        .filters(f -> f.rewritePath("/user_ms/register_user", userAuthPredicate+"/register_user"))
                        .uri(userMsURL))
                .route("register_adm", r -> r.path("/user_ms/register_adm")
                        .filters(f -> f.rewritePath("/user_ms/register_adm", userAuthPredicate+"/register_adm"))
                        .uri(userMsURL))
                .route("login", r -> r.path("/user_ms/login")
                        .filters(f -> f.rewritePath("/user_ms/login", userAuthPredicate+"/login"))
                        .uri(userMsURL))
                .route("verify_token", r -> r.path("/user_ms/verify_token")
                        .filters(f -> f.rewritePath("/user_ms/verify_token", userAuthPredicate+"/verify_token"))
                        .uri(userMsURL))

                // User Entity - Operations
                .route("getAll", r -> r.path("/user_ms/getAll")
                        .filters(f -> f.rewritePath("/user_ms/getAll", userOpPredicate))
                        .uri(userMsURL))
                .route("getByEmail", r -> r.path("/user_ms/getByEmail/{segment1}")
                        .filters(f -> f.rewritePath("/user_ms/getByEmail/(?<segment1>.*)", userOpPredicate+"/qe=${segment1}"))
                        .uri(userMsURL))
                .route("getById", r -> r.path("/user_ms/getById/{segment1}")
                        .filters(f -> f.rewritePath("/user_ms/getById/(?<segment1>.*)", userOpPredicate+"/${segment1}"))
                        .uri(userMsURL))
                .route("update_user", r -> r.path("/user_ms/update/{segment1}")
                        .filters(f -> f.rewritePath("/user_ms/update/(?<segment1>.*)", userOpPredicate+"/${segment1}"))
                        .uri(userMsURL))
                .route("delete_user", r -> r.path("/user_ms/delete/{segment1}")
                        .filters(f -> f.rewritePath("/user_ms/delete/(?<segment1>.*)", userOpPredicate+"/${segment1}"))
                        .uri(userMsURL))

                // Suggestion
                .route("suggest_new_game", r -> r.path("/suggest_ms/suggest")
                        .filters(f -> f.rewritePath("/suggest_ms/suggest", suggestionMsPredicate))
                        .uri(suggestionMsURL))
                .route("getAll_suggestion_ByEmail", r -> r.path("/suggest_ms/qe/{segment1}")
                        .filters(f -> f.rewritePath("/suggest_ms/qe/(?<segment1>.*)", suggestionMsPredicate+"/getAllByAuthor/qe=${segment1}"))
                        .uri(suggestionMsURL))
                .build();
    }

}