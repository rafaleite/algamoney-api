package br.com.rleite.algamoney.api.lancamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rleite.algamoney.api.lancamento.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
