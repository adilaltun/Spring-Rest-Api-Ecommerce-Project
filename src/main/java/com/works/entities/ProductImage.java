package com.works.entities;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    private Integer productId;

    @Lob
    private String file;

}