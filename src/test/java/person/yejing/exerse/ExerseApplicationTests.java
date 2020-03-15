package person.yejing.exerse;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest
class ExerseApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

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


        rabbitTemplate.convertAndSend("srmis.exchange", "a", "yejing222" );
    }

    @Test
    public void consumer(){
        Object o= rabbitTemplate.receiveAndConvert("srmis.queue");
        System.out.println(o);
    }

}
