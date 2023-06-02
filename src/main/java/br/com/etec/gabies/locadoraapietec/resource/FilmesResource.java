package br.com.etec.gabies.locadoraapietec.resource;

import br.com.etec.gabies.locadoraapietec.model.Filmes;
import br.com.etec.gabies.locadoraapietec.model.Genero;
import br.com.etec.gabies.locadoraapietec.repository.FilmesRepository;
import br.com.etec.gabies.locadoraapietec.repository.GeneroRepository;
import br.com.etec.gabies.locadoraapietec.repository.filter.FilmesFilter;
import br.com.etec.gabies.locadoraapietec.repository.filter.GeneroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmesResource {

    @Autowired
    private FilmesRepository filmesRepository;

    @GetMapping("/todos")
    public List<Filmes> listarTodosFilmes(){ return filmesRepository.findAll(Sort.by("nomefilme").ascending());}

    public Page<Filmes> pesquisar(FilmesFilter filmesFilter, Pageable pageable) {
        return filmesRepository.filtrar(filmesFilter, pageable);
    }


}
