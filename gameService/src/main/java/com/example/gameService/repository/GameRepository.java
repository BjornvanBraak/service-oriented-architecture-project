package com.example.gameService.repository;

import com.example.gameService.entity.Game;
import com.example.gameService.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game,Long> {

}
