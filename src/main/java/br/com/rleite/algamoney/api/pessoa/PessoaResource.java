package br.com.rleite.algamoney.api.pessoa;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rleite.algamoney.api._base.abstracts.AbstractResource;
import br.com.rleite.algamoney.api.constants.RolesConstants;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource extends AbstractResource<Pessoa, Long, PessoaService> {

	private static final long serialVersionUID = 1L;
	
	@GetMapping
	@PreAuthorize(RolesConstants.ROLE_PESQUISAR_PESSOA)
	public Page<Pessoa> findAll(Pageable pageable) {
		return super.findAll(pageable);
	}
	
	@PostMapping
	@PreAuthorize(RolesConstants.ROLE_CADASTRAR_PESSOA)
	public ResponseEntity<Pessoa> create(@Valid @RequestBody Pessoa entity, HttpServletResponse response) {
		return super.create(entity, response);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize(RolesConstants.ROLE_PESQUISAR_PESSOA)
	public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
		return super.findById(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize(RolesConstants.ROLE_REMOVER_PESSOA)
	public void delete(@PathVariable Long id) {
		super.delete(id);
	}

	@PutMapping("/{id}")
	@PreAuthorize(RolesConstants.ROLE_CADASTRAR_PESSOA)
	public ResponseEntity<Pessoa> update(@PathVariable Long id, @Valid @RequestBody Pessoa entity) {
		return super.update(id, entity);
	}
	
	@PutMapping("{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize(RolesConstants.ROLE_CADASTRAR_PESSOA)
	public void updateAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		this.service.updateAtivo(id, ativo);
	}

}
