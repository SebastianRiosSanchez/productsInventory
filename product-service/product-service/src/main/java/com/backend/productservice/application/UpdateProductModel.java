package com.backend.productservice.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateProductModel {

    @NotBlank(message = "Nombre es obligatorio")
    private String productName;

    @NotNull(message = "Precio es obligatorio")
    private Double productPrice;

    private Boolean isDelete;

    private Boolean isEnable;

}
