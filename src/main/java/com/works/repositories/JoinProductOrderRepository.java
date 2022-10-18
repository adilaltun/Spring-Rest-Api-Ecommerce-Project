package com.works.repositories;

import com.works.entities.JoinProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JoinProductOrderRepository extends JpaRepository<JoinProductOrder, Integer> {

    @Query(value = "SELECT * " +
            "FROM orders as o " +
            "INNER JOIN product as p on p.product_id=o.product_id " +
            "INNER JOIN user as u on o.user_id=u.user_id " +
            "ORDER BY u.user_id ASC", nativeQuery = true)
    List<JoinProductOrder> listOrders();

    @Query(value = "SELECT * " +
            "FROM orders as o INNER JOIN product AS p ON p.product_id=o.product_id " +
            "INNER JOIN user AS u ON o.user_id=u.user_id " +
            "WHERE o.user_id LIKE ?1",nativeQuery = true)
    List<JoinProductOrder> getOrderById(int orderId);
}