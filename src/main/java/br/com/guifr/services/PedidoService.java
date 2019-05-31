package br.com.guifr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guifr.domain.PedidoDomain;
import br.com.guifr.repositories.PedidoRepository;
import br.com.guifr.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	
	@Autowired
	private PedidoRepository repo;
	
	public PedidoDomain find(Integer id) {
		
		Optional<PedidoDomain> obj = repo.findById(id);		
		
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: " + id + ", Tipo: " + PedidoDomain.class.getName()));
		
	}
	
	public List<PedidoDomain> list() {
		
		return repo.findAll();
		
	}
	
	

}
