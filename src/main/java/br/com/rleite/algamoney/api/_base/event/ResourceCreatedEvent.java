package br.com.rleite.algamoney.api._base.event;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class ResourceCreatedEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response;
	private Serializable codigo;

	public ResourceCreatedEvent(Object source, HttpServletResponse pResponse, Serializable pCodigo ) {
		super(source);
		
		this.response = pResponse;
		this.codigo = pCodigo;
	}

}
