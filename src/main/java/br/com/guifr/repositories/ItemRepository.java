package br.com.guifr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.guifr.domain.ItemPedido;
import br.com.guifr.domain.ItemPedidoPK;

@Repository
public interface ItemRepository extends JpaRepository<ItemPedido, ItemPedidoPK>{

}
