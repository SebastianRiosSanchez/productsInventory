package com.backend.productservice.controller;

import com.backend.productservice.application.*;
import com.backend.productservice.entity.Product;
import com.backend.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Tag(name = "Products ", description = "Products  API.Contains all the operations that can be performed for Products ")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * @param product {{@link Product}}
     * @return Product
     * @nameMethod createProduct
     * @description Method to create a new product
     * @autor Sebastian Rios
     */
    @Operation(summary = "Get all advanced metrics",
            description = "Create a new product.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Product created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
            @ApiResponse(
                    responseCode = "400", description = "Error creating Product",
                    content = @Content
            )
    })
    @PostMapping("/create")
    public ResponseEntity<CustomResponseMs<ProductResponseModel>> createProduct(@RequestBody CreateProductModel product) {
        try {
            ProductResponseModel productResponseModel = this.productService.createProduct(product);
            return ResponseEntity.ok(
                    CustomResponseMs.<ProductResponseModel>builder()
                            .body(productResponseModel)
                            .responseMessage(
                                    new ResponseMessage(
                                            "Producto creado",
                                            "El producto se ha creado exitosamente",
                                            "201"
                                    ))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CustomResponseMs.<ProductResponseModel>builder()
                            .responseMessage(new ResponseMessage("Error al crear", e.getMessage(), "400"))
                            .build()
                    );
        }
    }


    /**
     * @param productId {{@link Integer}}
     * @return Optional<Product>
     * @nameMethod getById
     * @description Method to get a product record by id
     * @autor Sebastian Rios
     */
    @Operation(summary = "Get product by id",
            description = "Get a product record by its idS")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Product retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
            @ApiResponse(
                    responseCode = "400", description = "Error creating Product",
                    content = @Content
            )
    })
    @GetMapping("/{productId}")
    public ResponseEntity<CustomResponseMs<ProductResponseModel>> getById(@PathVariable Integer productId) {
        try {
            ProductResponseModel product = productService.getById(productId);

            return ResponseEntity.ok(
                    CustomResponseMs.<ProductResponseModel>builder()
                            .body(product)
                            .responseMessage(new ResponseMessage(
                                    "200",
                                    "Consulta exitosa",
                                    "Producto encontrado"
                            ))
                            .build()
            );
        } catch (CustomException e) {
            HttpStatus status = e.getCode().equals("404") ? HttpStatus.NOT_FOUND : HttpStatus.INTERNAL_SERVER_ERROR;

            return ResponseEntity
                    .status(status)
                    .body(CustomResponseMs.<ProductResponseModel>builder()
                            .responseMessage(new ResponseMessage(
                                    e.getCode(),
                                    e.getDescription(),
                                    e.getMessage()

                            ))
                            .build()
                    );
        }
    }


    /**
     * @param page {{@link Integer}}
     * @param size {{@link Integer}}
     * @return Page<Product>
     * @nameMethod getAllProducts
     * @description Method to get a product paginated list
     * @autor Sebastian Rios
     */
    @GetMapping()
    public ResponseEntity<?> getAllProducts(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(productService.getAllProducts(page, size));
    }

    /**
     * @param productId {{@link Integer}}
     * @param product   {{@link Product}}
     * @return Product
     * @nameMethod updateProduct
     * @description Method to update a product record
     * @autor Sebastian Rios
     */
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer productId, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(productId, product));
    }

    /**
     * @param productId {{@link Integer}}
     * @nameMethod deleteProduct
     * @description Method to delete a product record
     * @autor Sebastian Rios
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }


}
