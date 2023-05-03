package br.com.etec.gabies.locadoraapietec.repository;


import br.com.etec.gabies.locadoraapietec.model.Filmes;
import br.com.etec.gabies.locadoraapietec.repository.Filme.FilmeRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmesRepository extends JpaRepository<Filmes, Long>, FilmeRepositoryQuery {
}
