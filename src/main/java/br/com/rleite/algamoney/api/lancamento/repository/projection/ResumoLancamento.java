package br.com.rleite.algamoney.api.lancamento.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.rleite.algamoney.api.lancamento.ETipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResumoLancamento {
	
	private Long id;
	private String descricao;
	private LocalDate dataVencimento;
	private LocalDate dataPagamento;
	private BigDecimal valor;
	private ETipoLancamento tipo;
	private String categoria;
	private String pessoa;
	
}
