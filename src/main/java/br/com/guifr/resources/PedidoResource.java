package br.com.guifr.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.guifr.domain.PedidoDomain;
import br.com.guifr.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<PedidoDomain> listar() {
				
		List<PedidoDomain> list = pedidoService.list();
				
		return list;
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		
		PedidoDomain obj = pedidoService.find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}

}
