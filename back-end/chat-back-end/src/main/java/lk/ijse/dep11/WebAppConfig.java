package lk.ijse.dep11;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebAppConfig {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return new MethodValidationPostProcessor();
    }
}
