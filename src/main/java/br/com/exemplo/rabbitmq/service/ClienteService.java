package br.com.exemplo.rabbitmq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exemplo.rabbitmq.dto.ValidacaoDTO;
import br.com.exemplo.rabbitmq.entity.Cliente;
import br.com.exemplo.rabbitmq.entity.Whitelist;
import br.com.exemplo.rabbitmq.repository.ClienteRepository;
import br.com.exemplo.rabbitmq.repository.WhitelistRepository;
import br.com.exemplo.rabbitmq.utilitarios.Utils;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private WhitelistRepository whitelistRepository;
	
	public void salvar(Cliente cliente) {
		
		if(null != cliente.getClient() && !cliente.getClient().isEmpty()) {
			cliente.getWhitelist().add(new Whitelist(null, cliente.getRegex(), cliente));
			Cliente clienteRecuperado = clienteRepository.findByClient(cliente.getClient());
			if(null != clienteRecuperado && null != clienteRecuperado.getId()) {
				Whitelist whitelist = new Whitelist(null, cliente.getRegex(), clienteRecuperado);
				whitelistRepository.save(whitelist);
			}else {
				clienteRepository.save(cliente);
			}
		}else {
			Whitelist whitelist = new Whitelist(null, cliente.getRegex(), null);
			whitelistRepository.save(whitelist);
		}
	}
	
	public String validar(ValidacaoDTO validacaoDTO) {
		List<Whitelist> lista = new ArrayList<>();
		StringBuilder resposta = new StringBuilder("");
		
		//Recupera lista GLOBAL. Cliente == null
		lista = whitelistRepository.findByClienteIsNull();
		
		if(null != validacaoDTO.getClient()) {
			lista.addAll(whitelistRepository.findByCliente(validacaoDTO.getClient()));
		}
		
		if(!lista.isEmpty()) {
			boolean isCompativel = false;
			String regex = null;
			
			for(Whitelist w : lista) {
				isCompativel = Utils.isMatch(validacaoDTO.getUrl(), w.getRegex());
				if(isCompativel) {
					regex = w.getRegex();
					break;
				}
			}
			
			Utils.montaResposta(validacaoDTO, resposta, isCompativel, regex);
		}
		return resposta.toString();
	}

}
