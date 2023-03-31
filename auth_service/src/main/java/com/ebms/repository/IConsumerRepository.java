package com.ebms.repository;

import com.ebms.schema.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IConsumerRepository extends JpaRepository<Consumer, Integer> {
    Optional<Consumer> findByEmail(String email);
}
