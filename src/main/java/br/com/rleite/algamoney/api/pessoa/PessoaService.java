package br.com.rleite.algamoney.api.pessoa;

import org.springframework.stereotype.Service;

import br.com.rleite.algamoney.api._base.abstracts.AbstractService;

@Service
public class PessoaService extends AbstractService<Pessoa, Long, PessoaRepository> {

	private static final long serialVersionUID = 1L;

	public void updateAtivo(Long id, Boolean ativo) {
		Pessoa pessoaSalva = this.findOneOrThrowsExcepion(id);
		pessoaSalva.setAtivo(ativo);
		this.repository.save(pessoaSalva);
	}

}
