package br.com.guifr.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guifr.domain.CategoriaDomain;
import br.com.guifr.repositories.CategoriaRepository;
import br.com.guifr.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;	
	
	public CategoriaDomain find(Integer id) {		
				
		Optional<CategoriaDomain> obj = repo.findById(id); 
		
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
		"Objeto não encontrado! Id: " + id + ", Tipo: " + CategoriaDomain.class.getName())); 
		
		//return obj.orElse(null);
		
	}
	
}
