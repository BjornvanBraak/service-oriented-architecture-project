package com.example.gameService.controller;

import com.example.gameService.entity.Game;
import com.example.gameService.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping(value = "", params = "open")
    public Iterable<Game> fetchOpenGames(@RequestParam Boolean open){
        System.out.println(open);
        if(open){
            return gameService.findAllOpenGames();
        }
        //otherwise return empty array for now
        return new ArrayList<>();
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
