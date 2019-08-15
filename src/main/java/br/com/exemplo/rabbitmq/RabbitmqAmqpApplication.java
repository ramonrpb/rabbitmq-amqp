package br.com.exemplo.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableRabbit
public class RabbitmqAmqpApplication {
	
	@Value("${queue.order.name}")
    private String orderQueue;
	
	@Value("${queue.order.insert}")
	private String insertQueue;

	@Value("${queue.order.validate}")
	private String validateQueue;
	
	@Value("${queue.order.response.routing.key}")
	private String response;

	@Value("${exchange.response}")
	private String responseExchange;
	
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(responseExchange);
	}
	
	@Bean
    public Queue queue() {
        return new Queue(orderQueue, true);
    }
	
	@Bean
	public Queue queueInsert() {
		return new Queue(insertQueue, true);
	}
	
	@Bean
	public Queue queuevalidate() {
		return new Queue(validateQueue, true);
	}
	
	@Bean
	public Queue response() {
		return new Queue(response, true);
	}
	
	@Bean
    public Binding binding() {
		TopicExchange topic = topicExchange();
		Queue queueToBind = response();
        return BindingBuilder.bind(queueToBind)
            .to(topic)
            .with(response);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqAmqpApplication.class, args);
	}

}
