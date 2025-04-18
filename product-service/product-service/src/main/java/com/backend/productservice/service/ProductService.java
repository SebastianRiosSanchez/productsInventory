package com.backend.productservice.service;

import com.backend.productservice.entity.Product;
import com.backend.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * @param product {{@link Product}}
     * @return Product
     * @nameMethod createProduct
     * @description Method to create a new product
     * @autor Sebastian Rios
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
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
