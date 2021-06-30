package de.hsrm.mi.web.projekt.messaging;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class FotoMessageBrokerConfiguration {
    
    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.enableSimpleBroker("/topic");
    }

    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/messagebroker").setAllowedOrigins("*");
    }
}
