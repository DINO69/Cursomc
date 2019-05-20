package br.com.guifr.domain.enums;

public enum EstadoPagamentoEnum {

	PENDENTE(1,"Pedido Pendente"),
	QUITADO(2,"Pedido Quitado"),
	CANCELADO(3,"Pedido Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamentoEnum(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamentoEnum toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for (EstadoPagamentoEnum x : EstadoPagamentoEnum.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido " + cod);
	}
	
}
