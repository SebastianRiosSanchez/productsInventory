package com.backend.productservice.application;

import com.backend.productservice.entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateProductModel extends Product {

    @NotNull(message = "El campo Nombre es requerido")
    @NotEmpty(message = "El campo Nombre no puede estar vacio")
    private String productName;

    @NotNull(message = "El campo Precio es requerido")
    @NotEmpty(message = "El campo Precio no puede estar vacio")
    private Double productPrice;

}
