package gt.com.ad;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Sender {

    @Value(value = "${spring.rabbitmq.input.queue}")
    String inputqueue;

    @Value(value = "${spring.rabbitmq.input.exchange}")
    String exchange;

    @Value(value = "${spring.rabbitmq.input.routingkeyword}")
    String routingkey;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public String convertSendAndReceive(String message) {
        String msg = (String) this.rabbitTemplate.convertSendAndReceive(exchange, routingkey, message);
        return msg;
    }

}
