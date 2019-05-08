package br.com.guifr.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.guifr.domain.CategoriaDomain;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<CategoriaDomain> listar() {
		CategoriaDomain cat1 = new CategoriaDomain(1,"Informática");
		CategoriaDomain cat2 = new CategoriaDomain(2,"Escritório");
		
		List<CategoriaDomain> list = new ArrayList<>();
		list.add(cat1);
		list.add(cat2);
		
		return list;
	}
	
	
	
}
