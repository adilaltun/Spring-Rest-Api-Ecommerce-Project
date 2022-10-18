package com.works.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NotBlank(message = "Product name cannot be blank")
    @Length(min = 2 , max = 75 ,message = "Product name must be minimum 2 and maximum 75 character")
    private String productName;

    @NotBlank(message = "Product detail cannot be blank")
    @Length(min = 2 , max = 250 ,message = "Product detail must be minimum 2 and maximum 250 character")
    private String productDetail;

    @Min(value = 1,message = "Minimum value is 1")
    private Double productPrice;

    @NotNull(message = "Category id cannot be empty!")
    private Integer categoryId;



}


