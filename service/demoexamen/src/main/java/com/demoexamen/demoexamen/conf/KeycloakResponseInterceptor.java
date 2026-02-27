//package com.demoexamen.demoexamen.conf;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//@Configuration
//public class KeycloakResponseInterceptor {
//
//    @Bean
//    public WebClient keycloakWebClient() {
//        return WebClient.builder()
//                .baseUrl("http://keycloak:8080")
//                .filter(ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
//                    // Проверяем, что это ответ от /.well-known/openid-configuration
//                    if (clientResponse.request().getURI().getPath().contains(".well-known/openid-configuration")) {
//                        return clientResponse.bodyToMono(String.class)
//                                .map(body -> {
//                                    // Заменяем issuer в JSON ответе
//                                    String modifiedBody = body.replace(
//                                            "http://localhost:8090/realms/oAuth",
//                                            "http://keycloak:8080/realms/oAuth"
//                                    );
//                                    return clientResponse.mutate()
//                                            .body(modifiedBody)
//                                            .build();
//                                });
//                    }
//                    return Mono.just(clientResponse);
//                }))
//                .build();
//    }
//}