package br.com.guifr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guifr.domain.Categoria;
import br.com.guifr.repositories.CategoriaRepository;
import br.com.guifr.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;	
	
	public Categoria find(Integer id) {		
				
		Optional<Categoria> obj = repo.findById(id); 
		
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())); 
		
		//return obj.orElse(null);
		
	}
	
	public List<Categoria> list() {
		
		return repo.findAll();
		
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId()); // Verificar se o id esta cadastrado
		return repo.save(obj);
	}
	
}
