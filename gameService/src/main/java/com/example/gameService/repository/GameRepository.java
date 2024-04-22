package com.example.gameService.repository;

import com.example.gameService.entity.Game;
import com.example.gameService.entity.Game;
import com.example.gameService.entity.GameResult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game,Long> {
    @Query(value = "SELECT b FROM Game b WHERE b.id NOT IN (SELECT DISTINCT a.gameId FROM GameResult a)")
    public Iterable<Game> findAllGamesWithNoGameResult();
}
