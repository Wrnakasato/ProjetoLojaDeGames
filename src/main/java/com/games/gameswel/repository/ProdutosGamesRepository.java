package com.games.gameswel.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.games.gameswel.model.ProdutosGames;

@Repository
public interface ProdutosGamesRepository extends JpaRepository<ProdutosGames,Long>{
public List<ProdutosGames>findAllByNomeJogoContainingIgnoreCase(@Param("nomeJogo")String nomeJogo);
public List<ProdutosGames>findAllByPreco(@Param("preco")BigDecimal preco);

}
