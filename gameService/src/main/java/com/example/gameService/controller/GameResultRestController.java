package com.example.gameService.controller;

import com.example.gameService.entity.Game;
import com.example.gameService.entity.GameResult;
import com.example.gameService.service.GameResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/games/{gameId}/result")
public class GameResultRestController {
    @Autowired
    private GameResultService gameResultService;

    @GetMapping("")
    public GameResult fetchGameResult(@PathVariable("gameId") Long id){
        return gameResultService.findByGameId(id);
    }

    @PostMapping("")
    public GameResult saveGameResult(@Valid @RequestBody GameResult gameResult){
        return gameResultService.save(gameResult);
    }

    @GetMapping("test")
    public Iterable<GameResult> fetchAll(){
        return gameResultService.findAll();
    }

}
