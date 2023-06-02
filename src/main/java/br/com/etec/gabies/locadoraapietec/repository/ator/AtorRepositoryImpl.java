package br.com.etec.gabies.locadoraapietec.repository.ator;

import br.com.etec.gabies.locadoraapietec.model.Ator;
import br.com.etec.gabies.locadoraapietec.model.Genero;
import br.com.etec.gabies.locadoraapietec.repository.filter.AtorFilter;
import br.com.etec.gabies.locadoraapietec.repository.filter.GeneroFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AtorRepositoryImpl implements AtorRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Ator> filtrar(AtorFilter atorFilter, Pageable pageble) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Ator> criteria = builder.createQuery(Ator.class);
        Root<Ator> root = criteria.from(Ator.class);

        Predicate[] predicates = criarRestricoes(atorFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nomeator")));

        TypedQuery<Ator> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageble);

        return new PageImpl<>(query.getResultList(), pageble, total(atorFilter));
    }

    private Long total(AtorFilter atorFilter) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Ator> root = criteria.from(Ator.class);

        Predicate[] predicates = criarRestricoes(atorFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nomeator")));

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();


    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Ator> query, Pageable pageble) {

        int paginaAtual = pageble.getPageNumber();
        int totalRegistroPagina = pageble.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistroPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistroPagina);

    }

    private Predicate[] criarRestricoes(AtorFilter atorFilter, CriteriaBuilder builder, Root<Ator> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(atorFilter.getNomeator())) {
            predicates.add(builder.like(builder.lower(root.get("nomeator")),
                    "%" + atorFilter.getNomeator().toLowerCase() + "%"));
        }
        return predicates.toArray((new Predicate[predicates.size()]));

    }

}
