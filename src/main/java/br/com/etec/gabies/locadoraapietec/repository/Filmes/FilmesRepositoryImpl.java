package br.com.etec.gabies.locadoraapietec.repository.Filmes;

import br.com.etec.gabies.locadoraapietec.model.Filmes;
import br.com.etec.gabies.locadoraapietec.repository.filter.FilmesFilter;
import br.com.etec.gabies.locadoraapietec.repository.projections.ResumoFilmes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class FilmesRepositoryImpl implements FilmesRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<ResumoFilmes> filtrar(FilmesFilter filmesFilter, Pageable pageble) {
        return null;
    }
}
