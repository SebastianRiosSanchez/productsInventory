package com.backend.productservice.service;

import com.backend.productservice.application.CreateProductModel;
import com.backend.productservice.application.CustomException;
import com.backend.productservice.application.ProductResponseModel;
import com.backend.productservice.entity.Product;

public interface iProductService {

    ProductResponseModel createProduct(CreateProductModel product) throws CustomException;

}
