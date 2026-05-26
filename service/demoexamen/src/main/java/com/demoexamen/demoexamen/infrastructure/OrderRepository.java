package com.demoexamen.demoexamen.infrastructure;

import com.demoexamen.demoexamen.domain.Order;
import com.demoexamen.demoexamen.infrastructure.data.OrderReportProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("""
            SELECT
                ow.name AS counterpartyName,
                o.id AS orderNumber,
                p.name AS positionName,
                oi.count AS positionCount,
                (SELECT SUM(rp.count * i.price)
                    FROM RecipePosition  rp
                    JOIN rp.ingredient i
                    WHERE rp.product = oi.product
                ) AS positionPrice
            FROM Order o
                JOIN o.owner ow
                JOIN o.items oi   
                JOIN oi.product p           
            WHERE ow.name = :counterpartyName
            ORDER BY ow.name, o.id, p.name
            """)
    List<OrderReportProjection> getInfoByCounterpartyName(@Param("counterpartyName") String counterpartyName);

    @Query("""
            SELECT
                ow.name AS counterpartyName,
                o.id AS orderNumber,
                p.name AS positionName,
                oi.count AS positionCount,
                (SELECT SUM(rp.count * i.price)
                    FROM RecipePosition rp
                    JOIN rp.ingredient i
                    WHERE rp.product = oi.product
                ) AS positionPrice
            FROM Order o
                JOIN o.owner ow
                JOIN o.items oi   
                JOIN oi.product p           
            ORDER BY ow.name, o.id, p.name
            """)
    List<OrderReportProjection> getInfo();
}
