package com.example.bettingService.services;

import com.example.bettingService.entity.Bet;
import com.example.bettingService.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BetService implements BasicServiceInterface<Bet, Long> {
    @Autowired
    private BetRepository betRepository;

    @Override
    public Bet save(Bet bet) {
        return betRepository.save(bet);
    }

    @Override
    public Iterable<Bet> findAll() {
        return betRepository.findAll();
    }

    @Override
    public Optional<Bet> findById(Long id) {
        return betRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return betRepository.existsById(id);
    }

    @Override
    public Iterable<Bet> findAllById(Iterable<Long> ids) {
        return betRepository.findAllById(ids);
    }
}
