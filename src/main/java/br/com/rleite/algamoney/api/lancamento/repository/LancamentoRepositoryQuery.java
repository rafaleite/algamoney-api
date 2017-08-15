package br.com.rleite.algamoney.api.lancamento.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.rleite.algamoney.api.lancamento.Lancamento;
import br.com.rleite.algamoney.api.lancamento.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {
	public Page<Lancamento> findByFilter(LancamentoFilter filter, Pageable pageable);
	public Page<ResumoLancamento> findResume(LancamentoFilter filter, Pageable pageable);
}
