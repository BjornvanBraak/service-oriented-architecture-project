package com.example.gameService.repository;

import com.example.gameService.entity.GameResult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameResultRepository extends CrudRepository<GameResult,Long> {
    public GameResult findByGameId(Long gameId);
}
