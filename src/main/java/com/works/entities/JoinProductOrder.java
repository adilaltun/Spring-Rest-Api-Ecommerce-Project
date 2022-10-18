package com.works.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class JoinProductOrder {

    @Id
    private Integer orderId;
    private Integer productId;
    private Integer userId;

    private String userName;
    private String userSurname;
    private String productName;
    private Double productPrice;

}
