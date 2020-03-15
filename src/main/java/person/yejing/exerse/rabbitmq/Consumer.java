//package person.yejing.exerse.rabbitmq;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class Consumer {
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @RabbitListener(queues = "srmis.queue")
//    public void recive(String a){
//        System.out.println(a);
//    }
//}
