package com.corner.user.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        System.out.println("configureMessageBroker");
        config.enableSimpleBroker("/user/all","/all","/user/specific");
        config.setApplicationDestinationPrefixes("/user/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        System.out.println("registerStompEndpoints");
        registry.addEndpoint("/user/testchat");
        registry.addEndpoint("/user/testchat").withSockJS();
        registry.addEndpoint("/user/testchat").setAllowedOrigins("http://localhost:4200").withSockJS();
    }
}
//        config.enableSimpleBroker("/topic");
//        config.setApplicationDestinationPrefixes("/app");
//        config.enableSimpleBroker("/start");
//        config.setApplicationDestinationPrefixes("/current");