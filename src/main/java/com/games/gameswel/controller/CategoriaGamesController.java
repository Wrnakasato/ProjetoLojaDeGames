package com.games.gameswel.controller;

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

import com.games.gameswel.model.CategoriaGames;
import com.games.gameswel.repository.CategoriaGamesRepository;

@RestController 
@RequestMapping ("/categoria")
@CrossOrigin(origins = "*", allowedHeaders= "*")
public class CategoriaGamesController {

	@Autowired
	private CategoriaGamesRepository categoriaGamesRepository;
	
	@GetMapping
	public ResponseEntity <List<CategoriaGames>> getAll(){
	return ResponseEntity.ok(categoriaGamesRepository.findAll());	
	}
	
	@GetMapping("/{id}")
			public ResponseEntity<CategoriaGames> getById(@PathVariable Long id){
			return categoriaGamesRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
			
	}
	
	@GetMapping("/{descricao}")
	public ResponseEntity<CategoriaGames> getBydescricao(@PathVariable Long descricao){
		return categoriaGamesRepository.findById(descricao)
		.map(resposta -> ResponseEntity.ok(resposta))
      .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	
}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List< CategoriaGames >>getByDescricao(@PathVariable String descricao){
	return ResponseEntity.ok(categoriaGamesRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}	
	
	@PostMapping
	public ResponseEntity<CategoriaGames>post(@Valid @RequestBody CategoriaGames categoriaGames){
	return ResponseEntity.status(HttpStatus.CREATED)
	.body(categoriaGamesRepository.save(categoriaGames));
	}	
	
	@PutMapping
	public ResponseEntity<CategoriaGames>put(@Valid@RequestBody CategoriaGames categoriaGames){
	return categoriaGamesRepository.findById(categoriaGames.getId())
	 .map(resposta -> ResponseEntity.status(HttpStatus.OK)
	.body(categoriaGamesRepository.save(categoriaGames)))
	.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}
   
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id){
	Optional<CategoriaGames> categoriaGames = categoriaGamesRepository.findById(id);

	if(categoriaGames.isEmpty())
	throw new ResponseStatusException(HttpStatus.NOT_FOUND);

	categoriaGamesRepository.deleteById(id);
   }
}
