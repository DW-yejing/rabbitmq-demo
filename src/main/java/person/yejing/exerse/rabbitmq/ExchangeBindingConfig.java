/*
package person.yejing.exerse.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeBindingConfig {
    @Bean(name="fanoutExchange")
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange("Fanout-Exchange");
    }

    @Bean
    public Binding bingingQ1ToFanoutExchange(@Qualifier("First-Queue")Queue firstQueue, @Qualifier("fanoutExchange") FanoutExchange fanoutExchange){
        return BindingBuilder.bind(firstQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bingingQ2ToFanoutExchange(@Qualifier("Second-Queue")Queue secondQueue, @Qualifier("fanoutExchange") FanoutExchange fanoutExchange){
        return BindingBuilder.bind(secondQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bingingQ3ToFanoutExchange(@Qualifier("Third-Queue")Queue thirdQueue, @Qualifier("fanoutExchange") FanoutExchange fanoutExchange){
        return BindingBuilder.bind(thirdQueue).to(fanoutExchange);
    }

}
*/
