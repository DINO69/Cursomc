package br.com.guifr.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.guifr.domain.enums.TipoClienteEnum;

@Entity
public class ClienteDomain implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipoClienteEnum;
	
	@JsonManagedReference	
	@OneToMany(mappedBy = "cliente")
	private List<EnderecoDomain> enderecos = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	@JsonManagedReference
	//@JsonBackReference
	@OneToMany(mappedBy = "cliente")
	private List<PedidoDomain> pedidos = new ArrayList<>();
	
	public ClienteDomain() {
	}

	public ClienteDomain(Integer id, String nome, String email, String cpfOuCnpj, TipoClienteEnum tipoClienteEnum) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipoClienteEnum = tipoClienteEnum.getCod();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoClienteEnum getTipoClienteEnum() {
		return TipoClienteEnum.toEnum(tipoClienteEnum);
	}

	public void setTipoClienteEnum(TipoClienteEnum tipoClienteEnum) {
		this.tipoClienteEnum = tipoClienteEnum.getCod();
	}

	public List<EnderecoDomain> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoDomain> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	
	
	public List<PedidoDomain> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoDomain> pedidos) {
		this.pedidos = pedidos;
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
		ClienteDomain other = (ClienteDomain) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
