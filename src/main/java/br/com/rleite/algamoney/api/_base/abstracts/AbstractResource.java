package br.com.rleite.algamoney.api._base.abstracts;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.rleite.algamoney.api._base.event.ResourceCreatedEvent;

public class AbstractResource<T extends AbstractEntity<ID>, ID extends Serializable, S extends AbstractService<T, ID, ?>>
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	protected ApplicationEventPublisher publisher;
	
	@Autowired
	protected S service;
	
	public Page<T> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}
	
	public ResponseEntity<T> create(T entity, HttpServletResponse response) {
		T entitySave = service.save(entity);

		publisher.publishEvent(new ResourceCreatedEvent(this, response, entitySave.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(entitySave);
	}
	
	public ResponseEntity<T> findById(ID id) {
		T entity = service.findOne(id);

		return entity == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity);
	}
	
	public void delete(@PathVariable ID id) {
		this.service.delete(id);
	}

	public ResponseEntity<T> update(ID id, T entity) {
		T entitySave = service.update(id, entity);
		return ResponseEntity.ok(entitySave);
	}
	
	

}
