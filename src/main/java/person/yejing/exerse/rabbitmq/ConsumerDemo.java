package person.yejing.exerse.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerDemo {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "srmis.queue")
    public void recive(Message message, Channel channel){
        System.out.println(message);
        try {
            //定义队列的消费者
            Consumer consumer = new DefaultConsumer(channel);
            channel.basicConsume("srmis.queue", false, consumer);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
