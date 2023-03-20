package com.games.gameswel.controller;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.games.gameswel.model.ProdutosGames;
import com.games.gameswel.repository.CategoriaGamesRepository;
import com.games.gameswel.repository.ProdutosGamesRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin (origins= "*", allowedHeaders = "*")
public class ProdutosGamesController {

	@Autowired
	private ProdutosGamesRepository produtosGamesRepository;
	
	@Autowired
	private  CategoriaGamesRepository categoriaGamesRepository;
	
	@GetMapping
	public ResponseEntity<List<ProdutosGames>> getAll(){
	return ResponseEntity.ok(produtosGamesRepository.findAll());	
	}
	@GetMapping("/{id}")
	public ResponseEntity<ProdutosGames> getById(@PathVariable Long id){
	return produtosGamesRepository.findById(id)
	.map(resposta -> ResponseEntity.ok(resposta))
	.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/nomeJogo{nomeJogo}")
	public ResponseEntity<List<ProdutosGames>>getByNomeJogo(@PathVariable String nomeJogo){
		return ResponseEntity.ok(produtosGamesRepository.findAllByNomeJogoContainingIgnoreCase(nomeJogo));
	}

	@GetMapping("/preco{preco}")
	public ResponseEntity<List<ProdutosGames>>getByPreco(@PathVariable BigDecimal preco){
	return ResponseEntity.ok(produtosGamesRepository.findAllByPreco(preco));
		
}
	@PostMapping
	public ResponseEntity<ProdutosGames>post(@Valid @RequestBody ProdutosGames produtosGames){
	if(categoriaGamesRepository.existsById(produtosGames.getCategoria().getId()))
	
		return ResponseEntity.status(HttpStatus.CREATED)
	.body(produtosGamesRepository.save(produtosGames));
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}	
	
	@PutMapping
	public ResponseEntity<ProdutosGames>put(@Valid @RequestBody ProdutosGames produtosGames){

	if(produtosGamesRepository.existsById(produtosGames.getId())){

	if(categoriaGamesRepository.existsById(produtosGames.getCategoria().getId()))

	return ResponseEntity.status(HttpStatus.OK)
	.body(produtosGamesRepository.save(produtosGames));

	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}
   
   @ResponseStatus(HttpStatus.NO_CONTENT)
   @DeleteMapping("/{id}")
   public void delete (@PathVariable Long id){
   Optional <ProdutosGames>produtosGames= produtosGamesRepository.findById(id);
   
   if(produtosGames.isEmpty())
	   throw new ResponseStatusException(HttpStatus.NOT_FOUND);

   produtosGamesRepository.deleteById(id);
   }
}

