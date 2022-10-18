package com.works.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class JoinProCat {

    @Id
    private Integer productId;
    private Integer categoryId;

    private String productName;
    private Double productPrice;
    private String productDetail;
    private String categoryName;



}
