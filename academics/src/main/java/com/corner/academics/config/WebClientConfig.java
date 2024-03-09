package com.corner.academics.config;

import com.corner.academics.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
    @Autowired
    private LoadBalancedExchangeFilterFunction exchangeFilterFunction;
    @Bean
    public WebClient userWebClient(){
        return WebClient.builder()
                .baseUrl("http://user-service")
                .filter(exchangeFilterFunction)
                .build();
    }

    @Bean
    public UserClient userClient(){
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(userWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(UserClient.class);
    }
}
