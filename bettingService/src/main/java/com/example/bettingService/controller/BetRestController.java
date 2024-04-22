package com.example.bettingService.controller;

import com.example.bettingService.entity.Bet;
import com.example.bettingService.entity.BetStatus;
import com.example.bettingService.entity.CreateBetRequest;
import com.example.bettingService.services.BetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bets")
public class BetRestController {
    
    @Autowired
    private BetService betService;

    @GetMapping("")
    public Iterable<Bet> fetchBets(){
        return betService.findAll();
    }

    @GetMapping("/customers/{customerId}")
    public Iterable<Bet> fetchBetsOfCustomer(@PathVariable Long customerId){
        Iterable<Bet> bets = betService.findAllBetsOfCustomer(customerId);
        return bets;
    }

    @PostMapping("")
    public Bet saveBet(@Valid @RequestBody CreateBetRequest createBetRequest){
        return betService.placeBet(createBetRequest);
    }

    @GetMapping("/{id}")
    public Optional<Bet> fetchBet(@PathVariable("id") Long id){
        return betService.findById(id);
    }
}
