package com.example.gameService.service;

import com.example.gameService.entity.Game;
import com.example.gameService.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService implements BasicServiceInterface<Game, Long> {
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Iterable<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return gameRepository.existsById(id);
    }

    @Override
    public Iterable<Game> findAllById(Iterable<Long> ids) {
        return gameRepository.findAllById(ids);
    }

}
