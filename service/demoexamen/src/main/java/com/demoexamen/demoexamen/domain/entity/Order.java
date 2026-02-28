package com.demoexamen.demoexamen.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<OrderPosition> orderPositions;

    @Column(name = "created")
    private LocalDate created;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

    @PrePersist
    public void prePersist() {
        created = LocalDate.now();
    }

}
