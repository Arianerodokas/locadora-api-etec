package br.com.etec.gabies.locadoraapietec.resource;


import br.com.etec.gabies.locadoraapietec.model.Ator;
import br.com.etec.gabies.locadoraapietec.model.Genero;
import br.com.etec.gabies.locadoraapietec.repository.AtorRepository;
import br.com.etec.gabies.locadoraapietec.repository.GeneroRepository;
import br.com.etec.gabies.locadoraapietec.repository.filter.AtorFilter;
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
@RequestMapping("/ator")
public class AtorResource {

    @Autowired
    private AtorRepository atorRepository;

    @GetMapping("/todos")
    public List<Ator> listarTodosAtor(){
        return atorRepository.findAll(Sort.by("nomeator").ascending());
    }


    public Page<Ator> pesquisar(AtorFilter atorFilter, Pageable pageable) {
        return atorRepository.filtrar(atorFilter, pageable);
    }

}
