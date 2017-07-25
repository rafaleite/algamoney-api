package br.com.rleite.algamoney.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String msgUsuario = this.messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String msgDesenvolvedor = ex.getCause().toString();
		
		List<Erro> erros = Arrays.asList(new Erro(msgUsuario,msgDesenvolvedor)); 
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> erros = createErrorList(ex.getBindingResult()); 
		return handleExceptionInternal(ex, erros, headers, status, request);
	}
	
	private List<Erro> createErrorList(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			String msgUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String msgDesenvolvedor = fieldError.toString();
			erros.add(new Erro(msgUsuario, msgDesenvolvedor));
		}
		
		return erros;
	}
	
	
	@AllArgsConstructor
	@Getter
	@Setter
	public static class Erro {
		private String msgUsuario;
		private String msgDesenvolvedor;
	}

}
