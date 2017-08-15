package br.com.rleite.algamoney.api.usuario;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "usuario")
public class Usuario extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 2, max = 50)
	private String nome;

	@NotNull
	@Size(min = 2, max = 50)
	private String email;

	@NotNull
	@Size(min = 2, max = 150)
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_permissao", joinColumns = @JoinColumn(name = "fk_id_usuario")
		, inverseJoinColumns = @JoinColumn(name = "fk_id_permissao"))
	private List<Permissao> permissoes;

}
