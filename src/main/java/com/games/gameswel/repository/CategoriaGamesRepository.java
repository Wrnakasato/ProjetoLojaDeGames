package com.games.gameswel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.games.gameswel.model.CategoriaGames;

@Repository
public interface CategoriaGamesRepository extends JpaRepository <CategoriaGames,Long>{
	public List<CategoriaGames>findAllByDescricaoContainingIgnoreCase(@Param("descrição")String descricao);
	public List<CategoriaGames>findAllByPublicoContainingIgnoreCase(@Param("publico")String publico);
	}

