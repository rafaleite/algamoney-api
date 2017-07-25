package br.com.rleite.algamoney.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class ResourceCreatedEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response;
	private Long codigo;

	public ResourceCreatedEvent(Object source, HttpServletResponse pResponse, Long pCodigo ) {
		super(source);
		
		this.response = pResponse;
		this.codigo = pCodigo;
	}

}
