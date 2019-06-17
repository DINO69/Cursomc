package br.com.guifr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.guifr.domain.Cidade;
import br.com.guifr.domain.Cliente;
import br.com.guifr.domain.Endereco;
import br.com.guifr.domain.dto.ClienteDTO;
import br.com.guifr.domain.dto.ClienteNewDTO;
import br.com.guifr.domain.enums.TipoClienteEnum;
import br.com.guifr.repositories.ClienteRepository;
import br.com.guifr.repositories.EnderecoRepository;
import br.com.guifr.services.exceptions.DataIntegrityException;
import br.com.guifr.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		
		Optional<Cliente> obj = repo.findById(id);		
		
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
		
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId()); // Verificar se o id esta cadastrado
		updateData(newObj,obj);
		return repo.save(newObj);
	}	

	public void delete(Integer id) {
		find(id); // Verificar se o id esta cadastrado
		try {			
			repo.deleteById(id);			
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há produtos relacionadas.");
		}
	}

	public List<Cliente> findAll() {		
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {		
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {		
		Cliente cli1  = new Cliente(null, objDTO.getNome(),
										  objDTO.getEmail(),
										  objDTO.getCpfOuCnpj(),
										  TipoClienteEnum.toEnum(objDTO.getTipoClienteEnum()));
		
		Cidade cid1   = new Cidade(objDTO.getCidade(), null, null);
		
		Endereco end1 = new Endereco(null, objDTO.getLogradouro(), 
										   objDTO.getNumero(),
										   objDTO.getComplemento(),
										   objDTO.getBairro(),
										   objDTO.getCep(),
										   cli1,
										   cid1); 
		cli1.getEnderecos().add(end1);
		cli1.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2() != null) {
			cli1.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3() != null) {
			cli1.getTelefones().add(objDTO.getTelefone3());
		}
		
		return cli1;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());		
	}

}
