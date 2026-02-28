package com.demoexamen.demoexamen.infrastructure;

import com.demoexamen.demoexamen.domain.entity.Order;
import com.demoexamen.demoexamen.infrastructure.projection.OrderTotalProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
        SELECT 
            o.id as orderId,
            c.name as counterpartyName,
            p.name as productName,
            op.count as count,
            ((SELECT SUM(i.price * rp.weight / 1000) 
             FROM RecipePosition rp 
             JOIN rp.ingredient i 
             WHERE rp.product = p)
             * op.count)as price
        FROM Order o
        JOIN o.counterparty c
        JOIN o.orderPositions op
        JOIN op.product p
        ORDER BY o.id, p.name
        """)
    List<OrderTotalProjection> getTotal();
}
