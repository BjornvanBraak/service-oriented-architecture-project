package com.example.balanceService.controller;

import com.example.balanceService.entity.Balance;
import com.example.balanceService.services.BalanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/balances")
public class BalanceRestController {
    @Autowired
    private BalanceService balanceService;

    @GetMapping("")
    public Iterable<Balance> fetchBalances(){
        return balanceService.findAll();
    }

    @PostMapping("")
    public Balance saveBalance(@Valid @RequestBody Balance balance){
        return balanceService.save(balance);
    }

    @GetMapping("/{id}")
    public Optional<Balance> fetchBalance(@PathVariable("id") Long id){
        return balanceService.findById(id);
    }
}
