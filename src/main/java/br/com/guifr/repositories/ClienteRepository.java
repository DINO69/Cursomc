package br.com.guifr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.guifr.domain.ClienteDomain;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteDomain, Integer> {

}
