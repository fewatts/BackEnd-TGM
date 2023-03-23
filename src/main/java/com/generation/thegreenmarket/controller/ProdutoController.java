package com.generation.thegreenmarket.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.thegreenmarket.model.Produto;
import com.generation.thegreenmarket.repository.CategoriaRepository;
import com.generation.thegreenmarket.repository.ProdutoRepository;
import com.generation.thegreenmarket.repository.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}
	
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		
		if (usuarioRepository.existsById(produto.getUsuario().getIdUsuario())) {
			
			if (categoriaRepository.existsById(produto.getCategoria().getIdCategoria())) {
				return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
	}

}
