package com.example.identityAndAccessManagementService.repository;

import com.example.identityAndAccessManagementService.entity.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<Session, String> {
}
