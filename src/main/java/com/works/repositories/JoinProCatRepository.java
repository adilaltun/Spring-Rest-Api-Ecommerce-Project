package com.works.repositories;

import com.works.entities.JoinProCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JoinProCatRepository extends JpaRepository<JoinProCat, Integer> {

    @Query(value = "SELECT p.product_id, c.category_id, p.product_name, p.product_detail, p.product_price, c.category_name FROM product AS p INNER JOIN category AS c ON p.category_id=c.category_id",nativeQuery = true)
    List<JoinProCat> getProducts();

    @Query(value = "SELECT p.product_id, c.category_id, p.product_name, p.product_detail, p.product_price, c.category_name FROM product AS p INNER JOIN category AS c ON p.category_id=c.category_id WHERE p.product_id LIKE ?1",nativeQuery = true)
    Optional<JoinProCat> findProductById(Integer productId);

    @Query(value = "SELECT p.product_id, c.category_id, p.product_name, p.product_detail, p.product_price, c.category_name FROM product AS p INNER JOIN category AS c ON p.category_id=c.category_id WHERE p.product_name LIKE ?1",nativeQuery = true)
    List<JoinProCat> searchProduct(String q);

}