package br.com.guifr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.guifr.domain.CategoriaDomain;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaDomain, Integer> {

}
