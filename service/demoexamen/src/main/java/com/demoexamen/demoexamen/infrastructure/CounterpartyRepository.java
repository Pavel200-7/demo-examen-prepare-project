package com.demoexamen.demoexamen.infrastructure;

import com.demoexamen.demoexamen.domain.entity.Counterparty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
public interface CounterpartyRepository extends JpaRepository<Counterparty, Long> {
    boolean existsByName(String name);
    Optional<Counterparty> findByName(String name);
}
