package com.example.gameService.repository;

import com.example.gameService.entity.GameResult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameResultRepository extends CrudRepository<GameResult,Long> {

//    GameResult save(GameResult gameResult);
//    List<GameResult> findAll();
//    List<GameResult> findAllById(List<Long> ids);
//    Optional<GameResult> findById(Long id);
//    boolean existsById(Long id);
//
//    void deleteById(Long id);

    public GameResult findByGameId(Long gameId);
}
