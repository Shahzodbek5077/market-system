package com.example.marketsystem.controller;

import com.example.marketsystem.payload.ProductDto;
import com.example.marketsystem.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> get(@RequestParam(value = "page",defaultValue = "0") int page,
                                 @RequestParam(value = "size",defaultValue = "10") int size) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getPage(page,size));
    }


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProductDto productDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveOrEdit(null,productDto));
    }


    @PutMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProductDto productDto,
                                  @RequestParam(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveOrEdit(id,productDto));
    }

}
