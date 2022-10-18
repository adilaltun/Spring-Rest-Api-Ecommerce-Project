package com.works.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class JoinProCatImage extends Base {

    @Id
    private Integer productId;
    private Integer imageId;

    private String productName;
    private String categoryName;
    private String productDetail;
    private Double productPrice;
    private String file;

}
