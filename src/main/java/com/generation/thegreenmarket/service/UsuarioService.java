package com.generation.thegreenmarket.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.thegreenmarket.model.Usuario;
import com.generation.thegreenmarket.model.UsuarioLogin;
import com.generation.thegreenmarket.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			return Optional.empty();

		usuario.setSenhaUsuario(criptografarSenha(usuario.getSenhaUsuario()));

		return Optional.of(usuarioRepository.save(usuario));

	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		if (usuarioRepository.findById(usuario.getIdUsuario()).isPresent()) {

			Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());

			if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getIdUsuario() != usuario.getIdUsuario()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

			usuario.setSenhaUsuario(criptografarSenha(usuario.getSenhaUsuario()));

			return Optional.ofNullable(usuarioRepository.save(usuario));

		}

		return Optional.empty();

	}

	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

		if (usuario.isPresent()) {

			if (compararSenhas(usuarioLogin.get().getSenhaUsuario(), usuario.get().getSenhaUsuario())) {

				usuarioLogin.get().setIdUsuario(usuario.get().getIdUsuario());
				usuarioLogin.get().setNomeUsuario(usuario.get().getNomeUsuario());
				usuarioLogin.get().setCpfUsuario(usuario.get().getCpfUsuario());
				usuarioLogin.get().setEnderecoUsuario(usuario.get().getEnderecoUsuario());
				usuarioLogin.get().setTelefoneUsuario(usuario.get().getTelefoneUsuario());
				usuarioLogin.get().setCepUsuario(usuario.get().getCepUsuario());
				usuarioLogin.get().setFotoUsuario(usuario.get().getFotoUsuario());
				usuarioLogin.get()
						.setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenhaUsuario()));
				usuarioLogin.get().setSenhaUsuario(usuario.get().getSenhaUsuario());

				return usuarioLogin;

			}
		}

		return Optional.empty();

	}

	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(senha);

	}

	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(senhaDigitada, senhaBanco);

	}

	private String gerarBasicToken(String usuario, String senha) {

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}

}
