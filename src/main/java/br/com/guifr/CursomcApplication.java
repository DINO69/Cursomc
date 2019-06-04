package br.com.guifr;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;

import org.hibernate.query.criteria.internal.expression.function.LocateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.guifr.domain.CategoriaDomain;
import br.com.guifr.domain.CidadeDomain;
import br.com.guifr.domain.ClienteDomain;
import br.com.guifr.domain.EnderecoDomain;
import br.com.guifr.domain.EstadoDomain;
import br.com.guifr.domain.ItemPedidoDomain;
import br.com.guifr.domain.PagamentoComBoletoDomain;
import br.com.guifr.domain.PagamentoComCartaoDomain;
import br.com.guifr.domain.PagamentoDomain;
import br.com.guifr.domain.PedidoDomain;
import br.com.guifr.domain.ProdutoDomain;
import br.com.guifr.domain.enums.EstadoPagamentoEnum;
import br.com.guifr.domain.enums.TipoClienteEnum;
import br.com.guifr.repositories.CategoriaRepository;
import br.com.guifr.repositories.CidadeRepository;
import br.com.guifr.repositories.ClienteRepository;
import br.com.guifr.repositories.EnderecoRepository;
import br.com.guifr.repositories.EstadoRepository;
import br.com.guifr.repositories.ItemRepository;
import br.com.guifr.repositories.PagamentoRepository;
import br.com.guifr.repositories.PedidoRepository;
import br.com.guifr.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		CategoriaDomain cat1 = new CategoriaDomain(null, "Informática");
		CategoriaDomain cat2 = new CategoriaDomain(null, "Escritório");
		
		ProdutoDomain p1 = new ProdutoDomain(null, "Computador", 2000.00);
		ProdutoDomain p2 = new ProdutoDomain(null, "Impressora", 800.00);
		ProdutoDomain p3 = new ProdutoDomain(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		EstadoDomain est1 = new EstadoDomain(null,"Minas Gerais");
		EstadoDomain est2 = new EstadoDomain(null,"São Paulo");
		
		CidadeDomain c1 = new CidadeDomain(null, "Uberlândia", est1);
		CidadeDomain c2 = new CidadeDomain(null, "São Paulo", est2);
		CidadeDomain c3 = new CidadeDomain(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		ClienteDomain cli1 = new ClienteDomain(null, "Maria Silva", "maria@gmail.com",
					"12312312312", TipoClienteEnum.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		EnderecoDomain e1 = new EnderecoDomain(null, "Rua Flores", "300",
				"Apto 203", "Jardim", "38220834", cli1, c1);
		
		EnderecoDomain e2 = new EnderecoDomain(null, "Avenida Matos", "105",
				"Sala 800", "centro", "38777012", cli1, c2);
		
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		//Locale locale = new Locale("pt","BR");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");		
		sdf.setTimeZone(TimeZone.getTimeZone("UTC-3"));
		
		
		PedidoDomain ped1 = new PedidoDomain(null,
											sdf.parse("30/09/2017 10:32"),											
											cli1,
											e1);
		PedidoDomain ped2 = new PedidoDomain(null,
											sdf.parse("10/10/2017 19:35"),											
											cli1,
											e2);
		
		PagamentoDomain pagto1 = new PagamentoComCartaoDomain(null,
						  EstadoPagamentoEnum.QUITADO,
						  ped1, 
						  6);
		
		ped1.setPagamento(pagto1);
		
		PagamentoDomain pagto2 = new PagamentoComBoletoDomain(null,
									EstadoPagamentoEnum.PENDENTE,
									ped2,
									sdf.parse("20/10/2017 00:00"),
									null);

		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedidoDomain ip1 = new ItemPedidoDomain(ped1, p1, 0.00, 1, 2000.00);
		ItemPedidoDomain ip2 = new ItemPedidoDomain(ped1, p3, 0.00, 2, 80.00);
		ItemPedidoDomain ip3 = new ItemPedidoDomain(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}

}
