package br.com.exemplo.rabbitmq.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import br.com.exemplo.rabbitmq.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepositoryImplementation<Cliente, Long>{

	public Cliente findByClient(String client);

}
