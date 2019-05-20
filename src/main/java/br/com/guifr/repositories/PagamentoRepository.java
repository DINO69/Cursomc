package br.com.guifr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.guifr.domain.PagamentoDomain;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoDomain, Integer> {

}
