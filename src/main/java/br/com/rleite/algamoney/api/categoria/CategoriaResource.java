package br.com.rleite.algamoney.api.categoria;

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
@RequestMapping("/categorias")
public class CategoriaResource extends AbstractResource<Categoria, Long, CategoriaService> {

	private static final long serialVersionUID = 1L;
	
	@GetMapping
	@PreAuthorize(RolesConstants.ROLE_PESQUISAR_CATEGORIA)
	public Page<Categoria> findAll(Pageable pageable) {
		return super.findAll(pageable);
	}
	
	@PostMapping
	@PreAuthorize(RolesConstants.ROLE_CADASTRAR_CATEGORIA)
	public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria entity, HttpServletResponse response) {
		return super.create(entity, response);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize(RolesConstants.ROLE_PESQUISAR_CATEGORIA)
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		return super.findById(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize(RolesConstants.ROLE_REMOVERCATEGORIA)
	public void delete(@PathVariable Long id) {
		super.delete(id);
	}

	@PutMapping("/{id}")
	@PreAuthorize(RolesConstants.ROLE_CADASTRAR_CATEGORIA)
	public ResponseEntity<Categoria> update(@PathVariable Long id, @Valid @RequestBody Categoria entity) {
		return super.update(id, entity);
	}
	
}
