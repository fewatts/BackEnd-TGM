package com.generation.thegreenmarket.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@NotBlank(message = "O nome do usuário é obrigatório!")
	@Size(min = 3, max = 85, message = "O nome deve ter no mínimo 3 e no máximo 85 caracteres!")
	private String nomeUsuario;

	@CPF
	@NotBlank(message = "O CPF do usuário é obrigatório!")
	@Column(unique = true)
	private String cpfUsuario;

	@NotNull(message = "O endereço é obrigatório")
	private String enderecoUsuario;

	@NotNull
	private String telefoneUsuario;

	@NotBlank(message = "O CEP do usuário é obrigatório!")
	@Size(min = 8, max = 8, message = "O CEP deve ter 8 caracteres!")
	private String cepUsuario;

	@Schema(example = "email@email.com")
	@Email
	private String usuario;

	@NotBlank(message = "A senha do usuário é obrigatória!")
	@Size(min = 8, message = "A senha do usuário deve ter no mínimo 8!")
	private String senhaUsuario;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List<Produto> produtos;

	@Size(max = 5000, message = "O link da foto não pode ser maior do que 5000 caracteres")
	private String fotoUsuario;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public String getEnderecoUsuario() {
		return enderecoUsuario;
	}

	public void setEnderecoUsuario(String enderecoUsuario) {
		this.enderecoUsuario = enderecoUsuario;
	}

	public String getTelefoneUsuario() {
		return telefoneUsuario;
	}

	public void setTelefoneUsuario(String telefoneUsuario) {
		this.telefoneUsuario = telefoneUsuario;
	}

	public String getCepUsuario() {
		return cepUsuario;
	}

	public void setCepUsuario(String cepUsuario) {
		this.cepUsuario = cepUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public String getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

}
