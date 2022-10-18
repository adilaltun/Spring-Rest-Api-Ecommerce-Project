package com.works.repositories;

import com.works.entities.JoinProCatImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JoinProCatImageRepository extends JpaRepository<JoinProCatImage, Integer> {


    @Query(value = "SELECT *  " +
            "FROM product AS p LEFT JOIN product_image pi on pi.product_id=p.product_id " +
            "LEFT JOIN category c on p.category_id=c.category_id GROUP BY p.product_id",
            nativeQuery = true)
    List<JoinProCatImage> getImagesByProduct();

    @Query(value = "SELECT * " +
            "FROM product p LEFT JOIN product_image pi on pi.product_id=p.product_id " +
            "LEFT JOIN category c on p.category_id=c.category_id WHERE p.product_id LIKE ?1 " +
            "GROUP BY p.product_id",
            nativeQuery = true)
    List<JoinProCatImage> findImageWithProductId(Integer productId);

    @Query(value = "SELECT * " +
            "FROM product p LEFT JOIN category c on p.category_id=c.category_id " +
            "LEFT JOIN product_image pi on pi.product_id=p.product_id " +
            "WHERE c.category_id LIKE ?1 GROUP BY p.product_id",
            nativeQuery = true)
    List<JoinProCatImage> findProductWithCategory(Integer categoryId);




}