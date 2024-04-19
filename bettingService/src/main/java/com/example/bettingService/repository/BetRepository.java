package com.example.bettingService.repository;

import com.example.bettingService.entity.Bet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends CrudRepository<Bet,Long> {

}
