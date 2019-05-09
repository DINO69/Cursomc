package br.com.guifr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.guifr.domain.ProdutoDomain;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoDomain, Integer>{

}
