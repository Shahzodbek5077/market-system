package com.example.marketsystem.controller;

import com.example.marketsystem.payload.ApiResponse;
import com.example.marketsystem.payload.CategoryDto;
import com.example.marketsystem.service.CategoryService;
import com.example.marketsystem.utils.AppConstant;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveOrEdit(0L,categoryDto));
    }

    @GetMapping("/page")
    public HttpEntity<?> getPage(@RequestParam(value = "page",defaultValue =
                                        AppConstant.DEFAULT_PAGE) int page,
                                 @RequestParam(value = "size",defaultValue =
                                         AppConstant.DEFAULT_SIZE) int size) throws Exception {
        ApiResponse<?> pages = categoryService.getPage(page, size);
        return ResponseEntity.status(pages.isSuccess() ? 200 : 404).body(pages);
    }

    @PutMapping
    public HttpEntity<?> edit(@RequestParam Long id, @RequestBody CategoryDto categoryDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryService.saveOrEdit(id,categoryDto));
    }

    @DeleteMapping
    public HttpEntity<?> delete(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryService.delete(id));
    }

    @GetMapping("/one")
    public HttpEntity<?> getOne(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getOneCategory(id));
    }
}
