package person.yejing.exerse;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest
class ExerseApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    void contextLoads() {

    }

    @Test
    public void producer(){
        // 确认消息是否到达broker服务器,也就是只确认是否正确到达 Exchange 中
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String s) {
                if(ack){
                    System.out.println("发送成功");
                }else{
                    System.out.println(String.format("发送失败，失败原因:%s", s));
                }
            }
        });

        // 启动消息失败返回，比如路由不到队列时触发回调
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("check the variable");
            }
        });
        // 设置单条消息存活时间
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration("3000");
//        messageProperties.getConsumerTag()
        // 设置延迟，需要Rabbit Server3.6.x+安装并启用delay插件，同时需要设置exchange为x-delayed-message（另：可以通过TTL和死信队列实现）
        messageProperties.setDelay(2000000);
        Message message = new Message("yejing11111".getBytes(),messageProperties);
        rabbitTemplate.convertAndSend("srmis.exchange", "a", message );
    }

    @Test
    public void consumer(){
        rabbitTemplate.receiveAndReply("srmis.queue", new ReceiveAndReplyCallback<Order, String>(){

            @Override
            public String handle(Order payload) {
                return null;
            }
        });
    }

}
