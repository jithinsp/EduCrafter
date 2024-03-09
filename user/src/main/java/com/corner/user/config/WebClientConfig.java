package com.corner.user.config;

import com.corner.user.client.AcademicsService;
import com.corner.user.client.LoginClient;
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
                .baseUrl("http://auth-service")
                .filter(exchangeFilterFunction)
                .build();
    }

    @Bean
    public LoginClient userClient(){
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(userWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(LoginClient.class);
    }

    @Bean
    public WebClient academicsWebClient(){
        return WebClient.builder()
                .baseUrl("http://academics-service")
                .filter(exchangeFilterFunction)
                .build();
    }

    @Bean
    public AcademicsService academicsClient(){
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(academicsWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(AcademicsService.class);
    }
}
