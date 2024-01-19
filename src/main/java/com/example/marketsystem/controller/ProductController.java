package com.example.marketsystem.controller;

import com.example.marketsystem.payload.ProductDto;
import com.example.marketsystem.service.ProductService;
import com.example.marketsystem.utils.AppConstant;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(description = "Measure: KG, DONA, L, YASHIK, METR, KAROBKA, KUB, RULON")
    public HttpEntity<?> save(@RequestBody ProductDto productDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveOrEdit(0L, productDto));
    }

    @GetMapping("/page")
    public HttpEntity<?> getPage(@RequestParam(value = "page", defaultValue = AppConstant.DEFAULT_PAGE) int page,
                                 @RequestParam(value = "size", defaultValue = AppConstant.DEFAULT_SIZE) int size) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getPage(page,size));
    }

    @PutMapping
    @Operation(description = "Measure: KG, DONA, L, YASHIK, METR, KAROBKA, KUB, RULON")
    public HttpEntity<?> edit(@RequestParam Long id, @RequestBody ProductDto productDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.saveOrEdit(id,productDto));
    }

    @GetMapping("/one")
    public HttpEntity<?> getOne(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getOne(id));
    }

    @DeleteMapping
    private HttpEntity<?> delete(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.delete(id));
    }
}
