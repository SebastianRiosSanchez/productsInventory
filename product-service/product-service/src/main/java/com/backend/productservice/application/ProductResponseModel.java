package com.backend.productservice.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponseModel {

    private Integer idProduct;

    private String productName;

    private Double productPrice;

    private Boolean isDelete;

    private Boolean isEnable;

}
