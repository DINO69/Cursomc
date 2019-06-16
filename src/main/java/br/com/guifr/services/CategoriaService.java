package br.com.guifr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.guifr.domain.Categoria;
import br.com.guifr.domain.Cliente;
import br.com.guifr.domain.dto.CategoriaDTO;
import br.com.guifr.repositories.CategoriaRepository;
import br.com.guifr.services.exceptions.DataIntegrityException;
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
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId()); // Verificar se o id esta cadastrado
		updateData(newObj,obj);
		return repo.save(newObj);
	}	

	public void delete(Integer id) {
		find(id); // Verificar se o id esta cadastrado
		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir a categoria, produto associado!");
		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}

	public Categoria fromDTO(CategoriaDTO objDTO) {		
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());				
	}
	
}
