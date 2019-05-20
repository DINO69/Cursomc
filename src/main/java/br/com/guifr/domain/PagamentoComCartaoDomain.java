package br.com.guifr.domain;

import javax.persistence.Entity;

import br.com.guifr.domain.enums.EstadoPagamentoEnum;

@Entity
public class PagamentoComCartaoDomain extends PagamentoDomain {

	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PagamentoComCartaoDomain() {
		
	}

	public PagamentoComCartaoDomain(Integer id, EstadoPagamentoEnum estado, PedidoDomain pedido,Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;		
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
	
}
