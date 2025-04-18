package com.backend.productservice.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomResponseMs<T> {

    private T body;

    private ResponseMessage responseMessage;

}
