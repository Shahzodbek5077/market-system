package com.example.marketsystem.service;

import com.example.marketsystem.entity.Category;
import com.example.marketsystem.payload.ApiResponse;
import com.example.marketsystem.payload.CategoryDto;
import com.example.marketsystem.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ApiResponse<?> createCategory(CategoryDto categoryDto){
        if (categoryRepository.existsByNameEqualsIgnoreCase(categoryDto.getName())){
            Category category = Category.builder().name(categoryDto.getName()).build();
            categoryRepository.save(category);
            return new ApiResponse<>("Successfully saved category", true);
        }
        return new ApiResponse<>("Category name already exist", false);
    }

    public List<CategoryDto> getList(){
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            CategoryDto categoryDto = CategoryDto.builder().id(category.getId()).name(category.getName()).build();
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }
}
