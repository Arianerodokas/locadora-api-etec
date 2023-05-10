package br.com.etec.gabies.locadoraapietec.repository.Filme;

import br.com.etec.gabies.locadoraapietec.model.Filmes;
import br.com.etec.gabies.locadoraapietec.repository.Filme.FilmeRepositoryQuery;
import br.com.etec.gabies.locadoraapietec.repository.filter.FilmeFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class FilmeRepositoryImpl implements FilmeRepositoryQuery {
    @Override
    public Page<Filmes> filtrar(FilmeFilter filmeFilter, Pageable pageble) {
        return null;
    }
}
