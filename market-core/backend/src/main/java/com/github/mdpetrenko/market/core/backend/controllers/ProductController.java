package com.github.mdpetrenko.market.core.backend.controllers;

import com.github.mdpetrenko.market.core.api.dto.ProductDto;
import com.github.mdpetrenko.market.core.api.exceptions.CategoryNotFoundException;
import com.github.mdpetrenko.market.core.api.exceptions.ProductNotFoundException;
import com.github.mdpetrenko.market.core.api.exceptions.erors.CoreError;
import com.github.mdpetrenko.market.core.backend.converters.ProductConverter;
import com.github.mdpetrenko.market.core.backend.entities.Category;
import com.github.mdpetrenko.market.core.backend.entities.Product;
import com.github.mdpetrenko.market.core.backend.services.interfaces.CategoryService;
import com.github.mdpetrenko.market.core.backend.services.interfaces.ProductService;
import com.github.mdpetrenko.market.core.backend.validators.ProductValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Product methods")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

    @Operation(summary = "Returns page with products corresponding to Page Index and Page Size",
            responses = {
                    @ApiResponse(
                            description = "Successful operation", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @GetMapping
    public Page<ProductDto> findAll(
            @RequestParam(defaultValue = "1", name = "p") @Parameter(description = "Page index") Integer pageIndex,
            @RequestParam(defaultValue = "3", name = "s") @Parameter(description = "Page size") Integer pageSize,
            @RequestParam(required = false, name = "min_price") @Parameter(description = "Min price filter") Integer minPrice,
            @RequestParam(required = false, name = "max_price") @Parameter(description = "Max price filter") Integer maxPrice,
            @RequestParam(required = false, name = "title_part") @Parameter(description = "Part of title filter") String titlePart) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageSize < 1) {
            pageSize = 3;
        }
        return productService.findAll(pageIndex - 1, pageSize, minPrice, maxPrice, titlePart)
                .map(productConverter::entityToDto);
    }

    @Operation(summary = "Returns product by given id",
            responses = {
                    @ApiResponse(
                            description = "Successful operation", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                            description = "Product not found", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = CoreError.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id)
                .map(productConverter::entityToDto)
                .orElseThrow(() -> new ProductNotFoundException("Product id = " + id + " not found"));
    }

    @Operation(summary = "Save new Product in database with given parameters",
            responses = {
                    @ApiResponse(
                            description = "Product Created", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                            description = "Unknown Category given", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = CoreError.class))
                    ),
                    @ApiResponse(
                            description = "Invalid product (Price below zero or title is blank)", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = CoreError.class))
                    )
            }
    )
    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle())
                .orElseThrow(() -> new CategoryNotFoundException("Category title = " + productDto.getCategoryTitle() + " not found"));
        product.setCategory(category);
        product = productService.save(product);
        return productConverter.entityToDto(product);
    }

    @Operation(summary = "Delete Product from database by given id",
            responses = {
                    @ApiResponse(
                            description = "Product deleted", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                            description = "Product with such id not found", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = CoreError.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @Operation(summary = "Update Product parameters",
            responses = {
                    @ApiResponse(
                            description = "Product updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                            description = "Product with such id not found", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = CoreError.class))
                    ),
                    @ApiResponse(
                            description = "Invalid product (Price below zero or title is blank)", responseCode = "400",
                            content = @Content(schema = @Schema(implementation = CoreError.class))
                    )
            }
    )
    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productService.findById(productDto.getId())
                .orElseThrow(() -> new ProductNotFoundException("Product id = " + productDto.getId() + " not found"));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.findByTitle(productDto.getCategoryTitle())
                .orElseThrow(() -> new CategoryNotFoundException("Category title = " + productDto.getCategoryTitle() + " not found")));
        return productConverter.entityToDto(product);
    }
}
