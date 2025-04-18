package com.backend.productservice.service;

import com.backend.productservice.application.CreateProductModel;
import com.backend.productservice.application.CustomException;
import com.backend.productservice.application.ProductResponseModel;
import com.backend.productservice.entity.Product;


public interface iProductService {

    /**
     * @param product {{@link Product}}
     * @return Product
     * @nameMethod createProduct
     * @description Method to create a new product
     * @autor Sebastian Rios
     */
    ProductResponseModel createProduct(CreateProductModel product) throws CustomException;

    /**
     * @param productId {{@link Integer}}
     * @return Optional<Product>
     * @nameMethod getById
     * @description Method to get a product record by id
     * @autor Sebastian Rios
     */
    ProductResponseModel getById(Integer productId) throws CustomException;

}
