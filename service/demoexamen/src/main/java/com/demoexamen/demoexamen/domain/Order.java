package com.demoexamen.demoexamen.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "counterparty_id")
    private Counterparty owner;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}
