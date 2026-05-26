package com.demoexamen.demoexamen.infrastructure;

import com.demoexamen.demoexamen.domain.Counterparty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CounterpartyRepository extends JpaRepository<Counterparty, UUID> {
    boolean existsByName(String name);
}
