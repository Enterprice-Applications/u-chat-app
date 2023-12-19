package lk.ijse.dep11;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.dep11.wscontroller.ChatWSController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration

public class WebSocketConfig implements WebSocketConfigurer {

    @Primary
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean(){ // This is not necessary because we have Hibernate Validator
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ObjectMapper objectMapper(){ // This is not necessary because we have jackson
        return new ObjectMapper();
    }

    @Bean
    public ChatWSController chatWSController(){
        System.out.println("chatWSController()");
        return new ChatWSController();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        System.out.println("registerWebSocketHandlers()");
        registry.addHandler(chatWSController(), "/api/v3/messages").setAllowedOrigins("*");
    }
}