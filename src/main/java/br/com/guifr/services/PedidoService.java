package br.com.guifr.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.guifr.domain.ItemPedido;
import br.com.guifr.domain.PagamentoComBoleto;
import br.com.guifr.domain.Pedido;
import br.com.guifr.domain.enums.EstadoPagamentoEnum;
import br.com.guifr.repositories.ItemPedidoRepository;
import br.com.guifr.repositories.PagamentoRepository;
import br.com.guifr.repositories.PedidoRepository;
import br.com.guifr.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	
	@Autowired
	private PedidoRepository repo;
		
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemRepository;
	
	public Pedido find(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id);		
		
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		
	}
	
	public List<Pedido> findAll() {
		
		return repo.findAll();
		
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		//pedido
		obj.setId(null);
		obj.setInstante(new Date());
		
		//pagamento
		obj.getPagamento().setEstado(EstadoPagamentoEnum.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto,obj.getInstante());
		}
		
		//saves
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		//itens
		for (ItemPedido i : obj.getItens()) {
			i.setDesconto(0.00);
			i.setPreco(produtoService.find(i.getProduto().getId()).getPreco());
			i.setPedido(obj);
		}		
		
		itemRepository.saveAll(obj.getItens());		
		
		
		return obj;
		
		
	}
	
	

}
