/*
package person.yejing.exerse.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    @RabbitListener(queues = "First-Queue")
    public void firstConsumer(String msg){
        System.out.println(String.format("First-Queue's message: %s", msg));
    }

    @RabbitListener(queues = "SecondQueue")
    public void secondConsumer(String msg){
        System.out.println(String.format("SecondQueue's message: %s", msg));
    }

    @RabbitListener(queues = "ThirdQueue")
    public void thirdConsumer(String msg){
        System.out.println(String.format("ThirdQueue's message: %s", msg));
    }
}
*/
