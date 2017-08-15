package br.com.rleite.algamoney.api._base.abstracts;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public class AbstractService<T extends AbstractEntity<ID>, ID extends Serializable, R extends JpaRepository<T, ID>>
implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	protected R repository;
	
	public Page<T> findAll(Pageable pageable) {
		return this.repository.findAll(pageable);
	}

	public T findOne(ID id) {
		return repository.findOne(id);
	}
	
	public T save(T resource) {
		return this.repository.save(resource);
	}

	public void delete(ID id) {
		this.repository.delete(id);
	}
	
	public T update(ID id, T entity) {
		T entitySave = this.findOneOrThrowsExcepion(id);

		BeanUtils.copyProperties(entity, entitySave, "id");
		return repository.save(entitySave);
	}
	
	public T findOneOrThrowsExcepion(ID id) {
		T entity = repository.findOne(id);

		if (entity == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return entity;
	}
	

}
