package br.com.guifr.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonTypeName;

import br.com.guifr.domain.enums.EstadoPagamentoEnum;

@Entity
@JsonTypeName("pagamentoComBoleto") 
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;
	
	public PagamentoComBoleto() {
		
	}


	public PagamentoComBoleto(Integer id, EstadoPagamentoEnum estado, Pedido pedido,Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}


	public Date getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}


	public Date getDataPagamento() {
		return dataPagamento;
	}


	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
	
}
