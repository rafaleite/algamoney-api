package br.com.rleite.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rleite.algamoney.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
