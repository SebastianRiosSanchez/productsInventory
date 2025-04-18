package com.backend.productservice.service;

import com.backend.productservice.application.CreateProductModel;
import com.backend.productservice.application.CustomException;
import com.backend.productservice.application.ProductResponseModel;
import com.backend.productservice.entity.Product;
import com.backend.productservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements iProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository productRepository;

    /**
     * @param product {{@link Product}}
     * @return Product
     * @nameMethod createProduct
     * @description Method to create a new product
     * @autor Sebastian Rios
     */
    @Override
    public ProductResponseModel createProduct(CreateProductModel product) throws CustomException {
        try {
            Product savedProduct = this.productRepository.save(mapProductModelToProductEntity(product));
            return mapProductEntityToProductResponseModel(savedProduct);
        } catch (Exception exception) {
            CustomException newException = new CustomException(
                    "500",
                    exception.getMessage(),
                    exception.getMessage()
            );
            log.error(exception.getMessage());
            throw newException;
        }
    }

    /**
     * @param productModel {{@link CreateProductModel}}
     * @return Product
     * @nameMethod mapProductModelToProductEntity
     * @description Method to map a product model to product entity
     * @autor Sebastian Rios
     */
    private Product mapProductModelToProductEntity(CreateProductModel productModel) {
        Product productEntity = new Product();
        productEntity.setProductName(productModel.getProductName());
        productEntity.setProductPrice(productModel.getProductPrice());
        productEntity.setIsEnable(productModel.getIsEnable());
        productEntity.setIsDelete(productModel.getIsDelete());

        return productEntity;

    }

    /**
     * @param product {{@link ProductService}}
     * @return ProductResponseModel
     * @nameMethod mapProductEntityToProductResponseModel
     * @description Method to map a product entity to product response model
     * @autor Sebastian Rios
     */
    private ProductResponseModel mapProductEntityToProductResponseModel(Product product) {
        ProductResponseModel responseModel = new ProductResponseModel();
        responseModel.setProductName(product.getProductName());
        responseModel.setProductPrice(product.getProductPrice());
        responseModel.setIsDelete(product.getIsDelete());
        responseModel.setIsEnable(product.getIsEnable());

        return responseModel;

    }

    /**
     * @param productId {{@link Integer}}
     * @return Optional<Product>
     * @nameMethod getById
     * @description Method to get a product record by id
     * @autor Sebastian Rios
     */
    public Optional<Product> getById(Integer productId) {
        return this.productRepository.findById(productId);
    }

    /**
     * @param page {{@link Integer}}
     * @param size {{@link Integer}}
     * @return Page<Product>
     * @nameMethod getAllProducts
     * @description Method to get a product paginated list
     * @autor Sebastian Rios
     */
    public Page<Product> getAllProducts(Integer page, Integer size) {
        return this.productRepository.findAll(PageRequest.of(page, size));
    }

    /**
     * @param productId      {{@link Integer}}
     * @param updatedProduct {{@link Product}}
     * @return Product
     * @nameMethod updateProduct
     * @description Method to update a product record
     * @autor Sebastian Rios
     */
    public Product updateProduct(Integer productId, Product updatedProduct) {
        Product foundProduct = this.productRepository.findById(productId).orElseThrow();
        foundProduct.setProductName(updatedProduct.getProductName());
        foundProduct.setProductPrice(updatedProduct.getProductPrice());

        return this.productRepository.save(foundProduct);

    }

    /**
     * @param productId {{@link Integer}}
     * @nameMethod deleteProduct
     * @description Method to delete a product record
     * @autor Sebastian Rios
     */
    public void deleteProduct(Integer productId) {
        this.productRepository.deleteById(productId);
    }


}
