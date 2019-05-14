package br.com.guifr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.guifr.domain.EnderecoDomain;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoDomain, Integer> {

}
