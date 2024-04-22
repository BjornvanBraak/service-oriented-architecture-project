package com.example.identityAndAccessManagementService.repository;

import com.example.identityAndAccessManagementService.entity.Session;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<Session, String> {

    @Transactional
    void deleteAllByCustomerId(Long customerId);
}
