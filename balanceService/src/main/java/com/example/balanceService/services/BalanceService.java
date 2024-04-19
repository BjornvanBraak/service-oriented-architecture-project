package com.example.balanceService.services;

import com.example.balanceService.entity.Balance;
import com.example.balanceService.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BalanceService implements BasicServiceInterface<Balance, Long> {
    @Autowired
    private BalanceRepository balanceRepository;
    @Override
    public Balance save(Balance balance) {
        return balanceRepository.save(balance);
    }

    @Override
    public Iterable<Balance> findAll() {
        return balanceRepository.findAll();
    }

    @Override
    public Optional<Balance> findById(Long id) {
        return balanceRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return balanceRepository.existsById(id);
    }

    @Override
    public Iterable<Balance> findAllById(Iterable<Long> ids) {
        return balanceRepository.findAllById(ids);
    }
}
