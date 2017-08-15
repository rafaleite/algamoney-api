package br.com.rleite.algamoney.api.lancamento;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rleite.algamoney.api._base.abstracts.AbstractResource;
import br.com.rleite.algamoney.api._base.exceptionhandler.BaseExceptionHandler.Erro;
import br.com.rleite.algamoney.api.constants.RolesConstants;
import br.com.rleite.algamoney.api.lancamento.exceptions.PessoaInexistenteOuInativaException;
import br.com.rleite.algamoney.api.lancamento.repository.LancamentoFilter;
import br.com.rleite.algamoney.api.lancamento.repository.projection.ResumoLancamento;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource extends AbstractResource<Lancamento, Long, LancamentoService>{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping
	@PreAuthorize(RolesConstants.ROLE_PESQUISAR_LANCAMENTO)
	public Page<Lancamento> findAll(LancamentoFilter filter, Pageable pageable) {
		return service.findByFilter(filter, pageable);
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize(RolesConstants.ROLE_PESQUISAR_LANCAMENTO)
	public Page<ResumoLancamento> findResume(LancamentoFilter filter, Pageable pageable) {
		return service.findResume(filter, pageable);
	}
	
	@PostMapping
	@PreAuthorize(RolesConstants.ROLE_CADASTRAR_LANCAMENTO)
	public ResponseEntity<Lancamento> create(@Valid @RequestBody Lancamento entity, HttpServletResponse response) {
		return super.create(entity, response);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize(RolesConstants.ROLE_PESQUISAR_LANCAMENTO)
	public ResponseEntity<Lancamento> findById(@PathVariable Long id) {
		return super.findById(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize(RolesConstants.ROLE_REMOVER_LANCAMENTO)
	public void delete(@PathVariable Long id) {
		super.delete(id);
	}

	@PutMapping("/{id}")
	@PreAuthorize(RolesConstants.ROLE_CADASTRAR_LANCAMENTO)
	public ResponseEntity<Lancamento> update(@PathVariable Long id, @Valid @RequestBody Lancamento entity) {
		return super.update(id, entity);
	}
	
	
	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(PessoaInexistenteOuInativaException ex) {

		String msgUsuario = this.messageSource.getMessage("err.pessoa-inexistente-inativa", null,
				LocaleContextHolder.getLocale());
		String msgDesenvolvedor = ex.toString();

		List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesenvolvedor));
		
		return  ResponseEntity.badRequest().body(erros);
	}

}
