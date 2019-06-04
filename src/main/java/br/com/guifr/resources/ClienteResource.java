package br.com.guifr.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.guifr.domain.Cliente;
import br.com.guifr.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Cliente> listar() {
				
		List<Cliente> list = clienteService.list();
				
		return list;
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		
		Cliente obj = clienteService.find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	
	

}
