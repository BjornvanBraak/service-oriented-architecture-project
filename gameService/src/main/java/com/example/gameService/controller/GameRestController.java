package com.example.gameService.controller;

import com.example.gameService.entity.Game;
import com.example.gameService.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/games")
public class GameRestController {
    @Autowired
    private GameService gameService;

    @GetMapping("")
    public Iterable<Game> fetchGames(){
        return gameService.findAll();
    }

    @PostMapping("")
    public Game saveGame(@Valid @RequestBody Game game){
        return gameService.save(game);
    }

    @GetMapping("/{id}")
    public Optional<Game> fetchGame(@PathVariable("id") Long id){
        return gameService.findById(id);
    }
}
