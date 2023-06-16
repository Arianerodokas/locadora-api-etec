package br.com.etec.gabies.locadoraapietec.repository.Filmes;

import br.com.etec.gabies.locadoraapietec.model.Filmes;
import br.com.etec.gabies.locadoraapietec.model.Genero;
import br.com.etec.gabies.locadoraapietec.repository.filter.FilmesFilter;
import br.com.etec.gabies.locadoraapietec.repository.filter.GeneroFilter;
import br.com.etec.gabies.locadoraapietec.repository.projections.ResumoFilmes;
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

public class FilmesRepositoryImpl implements FilmesRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<ResumoFilmes> filtrar(FilmesFilter filmesFilter, Pageable pageable){
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery <ResumoFilmes> criteria = builder.createQuery(ResumoFilmes.class);
    Root<Filmes> root = criteria.from(Filmes.class);

    criteria.select(builder.construct(ResumoFilmes.class,
    root.get("id"),
    root.get("nomefilme"),
    root.get("genero").get("descricao"),
    root.get("ator").get("nomeator")

    ));

    Predicate[] predicates = criarRestricoes(filmesFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nomefilme")));

    TypedQuery<ResumoFilmes> query = manager.createQuery(criteria);
    adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(filmesFilter));
}
    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistroPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistroPagina);

    }

    private Long total(FilmesFilter filmesFilter) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Filmes> root = criteria.from(Filmes.class);

        Predicate[] predicates = criarRestricoes(filmesFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nomefilme")));

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();

    }
    private Predicate[] criarRestricoes(FilmesFilter filmesFilter, CriteriaBuilder builder, Root<Filmes> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(filmesFilter.getNomefilme())){
            predicates.add(builder.like(builder.lower(root.get("nomefilme")),
                    "%" + filmesFilter.getNomefilme().toLowerCase() + "%"));
        }

        if (!StringUtils.isEmpty(filmesFilter.getNomegenero())){
            predicates.add(builder.like(builder.lower(root.get("genero").get("descricao")),
                    "%" + filmesFilter.getNomefilme().toLowerCase() + "%"));
        }

        if (!StringUtils.isEmpty(filmesFilter.getNomeator())){
            predicates.add(builder.like(builder.lower(root.get("ator").get("nomeator")),
                    "%" + filmesFilter.getNomefilme().toLowerCase() + "%"));
        }

        return predicates.toArray((new Predicate[predicates.size()]));
    }
}
