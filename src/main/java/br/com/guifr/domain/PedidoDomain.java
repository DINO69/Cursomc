package br.com.guifr.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PedidoDomain implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", locale = "pt-BR")	
	private Date instante;
	
	//@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private PagamentoDomain pagamento;
	
	//@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private ClienteDomain cliente;
	
	
	@ManyToOne
	@JoinColumn(name="endereco_de_entrega_id")
	private EnderecoDomain endereoDeEntrega;
	
	
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedidoDomain> itens = new HashSet<>();
	
	public PedidoDomain() {
		
	}

	public PedidoDomain(Integer id, Date instante, PagamentoDomain pagamento, ClienteDomain cliente,
			EnderecoDomain endereoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.pagamento = pagamento;
		this.cliente = cliente;
		this.endereoDeEntrega = endereoDeEntrega;
	}
	
	public PedidoDomain(Integer id, Date instante, ClienteDomain cliente,
			EnderecoDomain endereoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;		
		this.cliente = cliente;
		this.endereoDeEntrega = endereoDeEntrega;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public PagamentoDomain getPagamento() {
		return pagamento;
	}

	public void setPagamento(PagamentoDomain pagamento) {
		this.pagamento = pagamento;
	}

	public ClienteDomain getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDomain cliente) {
		this.cliente = cliente;
	}

	public EnderecoDomain getEndereoDeEntrega() {
		return endereoDeEntrega;
	}

	public void setEndereoDeEntrega(EnderecoDomain endereoDeEntrega) {
		this.endereoDeEntrega = endereoDeEntrega;
	}

	public Set<ItemPedidoDomain> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedidoDomain> itens) {
		this.itens = itens;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoDomain other = (PedidoDomain) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
}
