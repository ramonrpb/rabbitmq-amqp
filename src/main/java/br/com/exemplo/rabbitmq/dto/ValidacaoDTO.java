package br.com.exemplo.rabbitmq.dto;

import java.io.Serializable;

public class ValidacaoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String client;
	private String url;
	private Integer correlationId;
	
	public ValidacaoDTO() {
	}
	
	public ValidacaoDTO(String cliente, String url, Integer correlationId) {
		super();
		this.client = cliente;
		this.url = url;
		this.correlationId = correlationId;
	}
	
	public String getClient() {
		return client;
	}

	public void setClient(String cliente) {
		this.client = cliente;
	}

	public Integer getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(Integer correlationId) {
		this.correlationId = correlationId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
