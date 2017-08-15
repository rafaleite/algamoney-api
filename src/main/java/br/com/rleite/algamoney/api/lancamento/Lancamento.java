package br.com.rleite.algamoney.api.lancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.rleite.algamoney.api._base.abstracts.AbstractEntity;
import br.com.rleite.algamoney.api.categoria.Categoria;
import br.com.rleite.algamoney.api.pessoa.Pessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lancamento")
public class Lancamento extends AbstractEntity<Long> {
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min=1, max=50)
	private String descricao;
	
	@NotNull
	private LocalDate dataVencimento;
	
	@NotNull
	private LocalDate dataPagamento;
	
	@NotNull
	private BigDecimal valor;
	
	@Size(max=100)
	private String observacao;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private ETipoLancamento tipo;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "fk_id_categoria")
	private Categoria categoria;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "fk_id_pessoa")
	private Pessoa pessoa;
	
}
