package br.com.guifr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.guifr.domain.EstadoDomain;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoDomain, Integer> {

}
