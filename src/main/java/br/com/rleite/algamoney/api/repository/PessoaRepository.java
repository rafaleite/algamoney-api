package br.com.rleite.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rleite.algamoney.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
