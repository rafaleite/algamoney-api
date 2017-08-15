package br.com.rleite.algamoney.api.lancamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.rleite.algamoney.api._base.abstracts.AbstractService;
import br.com.rleite.algamoney.api.lancamento.exceptions.PessoaInexistenteOuInativaException;
import br.com.rleite.algamoney.api.lancamento.repository.LancamentoFilter;
import br.com.rleite.algamoney.api.lancamento.repository.LancamentoRepository;
import br.com.rleite.algamoney.api.lancamento.repository.projection.ResumoLancamento;
import br.com.rleite.algamoney.api.pessoa.Pessoa;
import br.com.rleite.algamoney.api.pessoa.PessoaService;

@Service
public class LancamentoService extends AbstractService<Lancamento, Long, LancamentoRepository> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Override
	public Lancamento save(Lancamento resource) {
		Pessoa pessoa = pessoaService.findOne(resource.getPessoa().getId());
		
		if(pessoa == null || !pessoa.getAtivo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return this.repository.save(resource);
	}

	public Page<Lancamento> findByFilter(LancamentoFilter filter, Pageable pageable) {
		return this.repository.findByFilter(filter, pageable);
	}
	
	public Page<ResumoLancamento> findResume(LancamentoFilter filter, Pageable pageable) {
		return this.repository.findResume(filter, pageable);
	}

}
