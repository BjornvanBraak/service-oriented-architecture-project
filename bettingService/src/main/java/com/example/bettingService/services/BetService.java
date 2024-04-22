package com.example.bettingService.services;

import com.example.bettingService.entity.Bet;
import com.example.bettingService.entity.BetStatus;
import com.example.bettingService.entity.CreateBetRequest;
import com.example.bettingService.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Service
public class BetService implements BasicServiceInterface<Bet, Long> {
    @Autowired
    private BetRepository betRepository;

    public Iterable<Bet> findAllBetsOfCustomer(Long customerId){
        return betRepository.findAllByBetCreatorIdOrBetTakerId(customerId, customerId);
    }

    public Bet placeBet(CreateBetRequest createBetRequest){
        Bet bet = new Bet();
        bet.setBetValueCreator(createBetRequest.getBetValueCreator());
        bet.setBetValueTaker(createBetRequest.getBetValueTaker());
        bet.setBetOnHomeTeamCreator(createBetRequest.isBetOnHomeTeamCreator());
        bet.setBetStatus(BetStatus.BET_PLACEMENT_WAITING_FOR_ACCEPTOR);
        bet.setPayoutCreator(createBetRequest.getBetValueTaker());
        bet.setPayoutTaker(createBetRequest.getBetValueCreator());
        bet.setBetCreatorId(createBetRequest.getBetCreatorId());
        bet.setGameId(createBetRequest.getGameId());
        System.out.println(Objects.toString(bet));
        return this.save(bet);
    }

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
