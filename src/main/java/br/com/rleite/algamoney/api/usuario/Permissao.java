package br.com.rleite.algamoney.api.usuario;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import br.com.rleite.algamoney.api._base.abstracts.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
@DynamicUpdate
@Entity
@Table(name = "permissao")
public class Permissao extends AbstractEntity<Long>{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(min = 2, max = 50)
	private String descricao;
	
	
}
