package br.com.rleite.algamoney.api.lancamento.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.rleite.algamoney.api.categoria.Categoria_;
import br.com.rleite.algamoney.api.lancamento.Lancamento;
import br.com.rleite.algamoney.api.lancamento.Lancamento_;
import br.com.rleite.algamoney.api.lancamento.repository.projection.ResumoLancamento;
import br.com.rleite.algamoney.api.pessoa.Pessoa_;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<Lancamento> findByFilter(LancamentoFilter filter, Pageable pageable) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteriaQuery = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteriaQuery.from(Lancamento.class);
		
		Predicate[] predicates = createRestrictions(filter, builder, root);
		criteriaQuery.where(predicates);
		
		TypedQuery<Lancamento> query = entityManager.createQuery(criteriaQuery);
		addPageableRestrictions(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}
	
	@Override
	public Page<ResumoLancamento> findResume(LancamentoFilter filter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ResumoLancamento> criteriaQuery = builder.createQuery(ResumoLancamento.class);
		Root<Lancamento> root = criteriaQuery.from(Lancamento.class);
		
		criteriaQuery.select(builder.construct(ResumoLancamento.class
				, root.get(Lancamento_.id), root.get(Lancamento_.descricao)
				, root.get(Lancamento_.dataVencimento), root.get(Lancamento_.dataPagamento)
				, root.get(Lancamento_.valor), root.get(Lancamento_.tipo)
				, root.get(Lancamento_.categoria).get(Categoria_.nome)
				, root.get(Lancamento_.pessoa).get(Pessoa_.nome)));
		
		Predicate[] predicates = createRestrictions(filter, builder, root);
		criteriaQuery.where(predicates);
		
		TypedQuery<ResumoLancamento> query = entityManager.createQuery(criteriaQuery);
		addPageableRestrictions(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}

	
	private Predicate[] createRestrictions(LancamentoFilter filter, CriteriaBuilder builder, Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(filter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get(Lancamento_.descricao)), "%" + filter.getDescricao().toLowerCase() + "%"));
		}
		
		if (filter.getDataVencimentoInicio() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), filter.getDataVencimentoInicio()));
		}
		
		if (filter.getDataVencimentoFim() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), filter.getDataVencimentoFim()));
		}
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void addPageableRestrictions(TypedQuery<?> query, Pageable pageable) {
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
	}
	
	private Long total(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] predicates = createRestrictions(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return entityManager.createQuery(criteria).getSingleResult();
	}

}
