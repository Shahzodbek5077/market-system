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

    public ApiResponse<?> saveOrEdit(Long id, ProductDto productDto){
        Product product;
        if (id==0){
            product = Product.builder()
                    .name(productDto.getName())
                    .measureCount(productDto.getMeasureCount())
                    .price(productDto.getPrice())
                    .percentage(productDto.getPercentage())
                    .category(categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> GenericNotFoundException.builder().message("Not found").statusCode(404).build()))
                    .measure(measure(productDto.getMeasure())).build();
        }else {
            product = productRepository.findById(id).orElseThrow(() -> GenericNotFoundException.builder().message("Not found").statusCode(404).build());
            product.setName(productDto.getName());
            product.setMeasureCount(productDto.getMeasureCount());
            product.setPrice(productDto.getPrice());
            product.setPercentage(productDto.getPercentage());
            product.setCategory(categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> GenericNotFoundException.builder().message("Not found").statusCode(404).build()));
            product.setMeasure(measure(productDto.getMeasure()));
        }
        productRepository.save(product);
        return new ApiResponse<>("Successfully completed product", true);
    }

    public ProductDto productDtoObj(Product product){
        return new ProductDto(product.getId(), product.getName(), product.getMeasureCount(), product.getPrice(), product.getPercentage(), product.getCategory().getId(), product.getMeasure().name());
    }

    public ApiResponse<?> getPage(int page, int size) throws Exception {
        Page<Product> allBy = productRepository.findAllBy(CommonUtils.getPageable(page, size));
        if (!allBy.isEmpty()){
            return ApiResponse.builder()
                    .success(true)
                    .body(ResPageable.builder().page(page).size(size).totalElements(allBy.getTotalElements()).totalPage(allBy.getTotalPages()
                    ).object(new ArrayList<>(allBy.stream().map(this::productDtoObj).toList())).build()).message("Success").build();
        }
        return new ApiResponse<>("Product list empty", false);
    }

    public Measure measure(String name){
        for (Measure value : Measure.values()) {
            if (value.name().equalsIgnoreCase(name)){
                return value;
            }
        }
        return null;
    }

    public ProductDto getOne(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> GenericNotFoundException.builder().message("Not found").statusCode(404).build());
        return productDtoObj(product);
    }

    public ApiResponse<?> delete(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> GenericNotFoundException.builder().message("Not found").statusCode(404).build());
        productRepository.delete(product);
        return new ApiResponse<>("Successfully deleted product", true);
    }
}
