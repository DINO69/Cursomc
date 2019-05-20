package br.com.guifr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.guifr.domain.PedidoDomain;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoDomain, Integer> {

}
