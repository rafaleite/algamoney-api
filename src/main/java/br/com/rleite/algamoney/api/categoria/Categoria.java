package br.com.rleite.algamoney.api.categoria;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.rleite.algamoney.api._base.abstracts.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
@Table(name = "categoria")
@Entity
public class Categoria extends AbstractEntity<Long> {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(min=3, max=20)
	private String nome;
}
