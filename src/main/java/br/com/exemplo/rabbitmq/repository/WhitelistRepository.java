package br.com.exemplo.rabbitmq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.exemplo.rabbitmq.entity.Whitelist;

@Repository
public interface WhitelistRepository extends JpaRepositoryImplementation<Whitelist, Long>{

	public List<Whitelist> findByClienteIsNull();

	@Query(value = "SELECT w FROM Whitelist w WHERE w.cliente.client = :client ")
	public List<Whitelist> findByCliente(@Param( "client" )String cliente);
	
}
