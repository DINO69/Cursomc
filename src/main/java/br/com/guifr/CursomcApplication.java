package br.com.guifr;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.guifr.domain.CategoriaDomain;
import br.com.guifr.domain.CidadeDomain;
import br.com.guifr.domain.ClienteDomain;
import br.com.guifr.domain.EnderecoDomain;
import br.com.guifr.domain.EstadoDomain;
import br.com.guifr.domain.ProdutoDomain;
import br.com.guifr.domain.enums.TipoClienteEnum;
import br.com.guifr.repositories.CategoriaRepository;
import br.com.guifr.repositories.CidadeRepository;
import br.com.guifr.repositories.ClienteRepository;
import br.com.guifr.repositories.EnderecoRepository;
import br.com.guifr.repositories.EstadoRepository;
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
		
	}

}
