package br.com.etec.gabies.locadoraapietec.repository.Filme;

import br.com.etec.gabies.locadoraapietec.model.Filmes;
import br.com.etec.gabies.locadoraapietec.model.Genero;
import br.com.etec.gabies.locadoraapietec.repository.filter.FilmeFilter;
import br.com.etec.gabies.locadoraapietec.repository.filter.GeneroFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FilmeRepositoryQuery {

    public Page<Filmes> filtrar(FilmeFilter filmeFilter, Pageable pageble);
}
