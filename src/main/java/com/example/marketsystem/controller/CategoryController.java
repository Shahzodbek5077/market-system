package com.example.marketsystem.controller;

import com.example.marketsystem.payload.ApiResponse;
import com.example.marketsystem.payload.CategoryDto;
import com.example.marketsystem.service.CategoryService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public HttpEntity<?> save(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDto));
    }

    @GetMapping("/list")
    public HttpEntity<?> get(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getList());
    }

}
