package br.com.etec.gabies.locadoraapietec.repository.Genero;

import br.com.etec.gabies.locadoraapietec.model.Genero;
import br.com.etec.gabies.locadoraapietec.repository.filter.GeneroFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GeneroRepositoryQuery {

    public Page<Genero> filtrar(GeneroFilter generoFilter, Pageable pageble);
}
