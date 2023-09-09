package gt.com.ad.config;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gt.com.ad.Sender;



@Configuration
public class RabbitMQConfiguration {
    

    @Bean
    public Sender sender() {
        return new Sender();
    }


    @Bean
    public MessageConverter messageConverter() {
        return new SimpleMessageConverter();
    }


}
