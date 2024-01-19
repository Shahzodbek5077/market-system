package com.example.marketsystem.service;

import com.example.marketsystem.entity.Product;
import com.example.marketsystem.entity.template.Measure;
import com.example.marketsystem.exception.GenericNotFoundException;
import com.example.marketsystem.payload.ApiResponse;
import com.example.marketsystem.payload.ProductDto;
import com.example.marketsystem.payload.ResPageable;
import com.example.marketsystem.repository.CategoryRepository;
import com.example.marketsystem.repository.ProductRepository;
import com.example.marketsystem.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;



    public ApiResponse<?> saveOrEdit(Long id, ProductDto productDto) {
        Product product = id != null ? productRepository.findById(id).orElseThrow(() ->
                GenericNotFoundException.builder().message("success").statusCode(204).build()) : new Product();
        product.setName(productDto.getName());
        product.setMeasureCount(productDto.getMeasureCount());
        product.setPrice(productDto.getPrice());
        product.setPercentage(productDto.getPercentage());
        product.setCategory(categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() ->
                GenericNotFoundException.builder().message("Category not found ").statusCode(204).build()));
        product.setMeasure(measure(productDto.getMeasure()));
        productRepository.save(product);
        return ApiResponse.builder().message("success").build();
    }


    public Measure measure(String measure) {
        for (Measure value : Measure.values()) {
            if (value.name().equalsIgnoreCase(measure)) return value;
        }
        return null;
    }


    public ProductDto getOne(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> GenericNotFoundException.builder().message("Not found").statusCode(404).build());
        return getProductDto(product);
    }


    public ApiResponse<?> delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> GenericNotFoundException.builder().message("Not found").statusCode(404).build());
        productRepository.delete(product);
        return new ApiResponse<>("Successfully deleted product", true);
    }


    public ApiResponse<?> getPage(int page, int size) throws Exception {
        Page<Product> pages = productRepository.findAllBy(CommonUtils.getPageable(page, size));
        if (!pages.isEmpty()) {
            return ApiResponse.builder().body(ResPageable.builder().page(page).size(size).totalPage(pages.getTotalPages())
                    .totalElements(pages.getTotalElements())
                    .object(new ArrayList<>(pages.stream().map(this::getProductDto).toList())).build()).message("Success").build();
        } else return ApiResponse.builder().message("Product list empty").success(false).build();
    }


    public ProductDto getProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .measureCount(product.getMeasureCount())
                .price(product.getPrice())
                .percentage(product.getPercentage())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : 0)
                .categoryName(product.getCategory().getName())
                .measure(product.getMeasure() != null ? product.getMeasure().name() : "").build();
    }

}
