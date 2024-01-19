package com.example.marketsystem.service;

import com.example.marketsystem.entity.Category;
import com.example.marketsystem.exception.GenericNotFoundException;
import com.example.marketsystem.payload.ApiResponse;
import com.example.marketsystem.payload.CategoryDto;
import com.example.marketsystem.payload.ResPageable;
import com.example.marketsystem.repository.CategoryRepository;
import com.example.marketsystem.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ApiResponse<?> saveOrEdit(Long id, CategoryDto categoryDto) {
        Category category;
        if (!categoryRepository.existsByNameEqualsIgnoreCase(categoryDto.getName())) {
            if (id == 0) {
                category = Category.builder().name(categoryDto.getName()).build();
            } else {
                category = categoryRepository.findById(id).orElseThrow(() -> GenericNotFoundException.builder().message("Not found").statusCode(204).build());
                category.setName(categoryDto.getName());
            }
            categoryRepository.save(category);
            return new ApiResponse<>("Successfully saved category", true);
        }
        return new ApiResponse<>("Category name already exist", false);
    }

    public CategoryDto categoryDtoObj(Category category){
        return new CategoryDto(category.getId(), category.getName());
    }

    public ApiResponse<?> getPage(int page, int size) throws Exception {
        Page<Category> categories = categoryRepository.findAllBy(CommonUtils.getPageable(page, size));
        if(!categories.isEmpty()) {
            return ApiResponse.builder()
                    .success(true)
                    .body(ResPageable.builder().page(page).size(size).totalElements(categories.getTotalElements())
                            .totalPage(categories.getTotalPages())
                            .object(new ArrayList<>(categories.stream().map(this::categoryDtoObj).toList())).build()
                    )
                    .message("Successfully")
                    .build();
        }else return ApiResponse.builder().message("Category list empty").success(false).build();
    }

    public CategoryDto getOneCategory(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> GenericNotFoundException.builder().message("Not found").statusCode(204).build());
        return categoryDtoObj(category);
    }

    public ApiResponse<?> delete(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> GenericNotFoundException.builder().message("Not found").statusCode(204).build());
        categoryRepository.delete(category);
        return new ApiResponse<>("Deleted category", true);
    }
}
