package br.com.exemplo.rabbitmq.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.exemplo.rabbitmq.dto.ValidacaoDTO;
import br.com.exemplo.rabbitmq.entity.Cliente;
import br.com.exemplo.rabbitmq.service.ClienteService;
 
@Component
public class OrderConsumer {
	
	@Value("${queue.order.insert}")
	private String insertQueue;

	@Value("${queue.order.validate}")
	private String validateQueue;
	
	@Value("${exchange.response}")
	private String responseExchange;
	
	@Value("${queue.order.response.routing.key}")
	private String responseRoutingKey;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
 
    @RabbitListener(queues = "#{'${queue.order.names}'.split(',')}")
    public void receiveMessage(Message message) {
    	
    	String fila = message.getMessageProperties().getConsumerQueue();
    	String mensagem = new String(message.getBody());
    	
    	System.out.println("\n\nFila: " + fila);
    	System.out.println("Mensagem: " + mensagem);
    	
    	Gson gson = new Gson();
    	if(fila.equals(insertQueue)) {
    		Cliente cliente = gson.fromJson(mensagem, Cliente.class);
        	clienteService.salvar(cliente);
    	}else if(fila.equals(validateQueue)) {
    		ValidacaoDTO validacaoDTO = gson.fromJson(mensagem, ValidacaoDTO.class);
    		String resposta = clienteService.validar(validacaoDTO);
    		rabbitTemplate.convertAndSend(responseExchange, responseRoutingKey, resposta);
    	}
    	
    }
}