package com.example.gameService.service;

import com.example.gameService.entity.GameResult;
import com.example.gameService.repository.GameResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameResultService implements BasicServiceInterface<GameResult, Long> {

    @Autowired
    private GameResultRepository gameResultRepository;


    public GameResult findByGameId(Long gameId){
        return gameResultRepository.findByGameId(gameId);
    }

    @Override
    public GameResult save(GameResult gameResult) {
        return gameResultRepository.save(gameResult);
    }

    @Override
    public Iterable<GameResult> findAll() {
        return gameResultRepository.findAll();
    }

    @Override
    public Optional<GameResult> findById(Long id) {
        return gameResultRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return gameResultRepository.existsById(id);
    }

    @Override
    public Iterable<GameResult> findAllById(Iterable<Long> ids) {
        return gameResultRepository.findAllById(ids);
    }

}
