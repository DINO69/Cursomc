package br.com.guifr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guifr.domain.CategoriaDomain;
import br.com.guifr.domain.ClienteDomain;
import br.com.guifr.repositories.ClienteRepository;
import br.com.guifr.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public ClienteDomain find(Integer id) {
		
		Optional<ClienteDomain> obj = repo.findById(id);		
		
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: " + id + ", Tipo: " + ClienteDomain.class.getName()));
		
	}
	
	public List<ClienteDomain> list() {
		
		return repo.findAll();
		
	}

}
