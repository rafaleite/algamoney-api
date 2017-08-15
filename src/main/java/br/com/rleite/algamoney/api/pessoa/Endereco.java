package br.com.rleite.algamoney.api.pessoa;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Endereco {
	@Size(max=30)
	private String logradouro;
	
	@Size(max=30)
	private String numero;
	
	@Size(max=30)
	private String complemento;
	
	@Size(max=30)
	private String bairro;
	
	@Size(max=30)
	private String cep;
	
	@Size(max=30)
	private String cidade;
	
	@Size(max=30)
	private String estado;

}
