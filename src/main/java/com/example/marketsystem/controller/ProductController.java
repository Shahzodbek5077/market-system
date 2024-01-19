package com.example.marketsystem.controller;

import com.example.marketsystem.payload.ProductDto;
import com.example.marketsystem.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
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
    public HttpEntity<?> getPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "size", defaultValue = "10") int size) throws Exception {
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

    @Operation(description = "Measure: KG, DONA, L, YASHIK, METR, KAROBKA, KUB, RULON")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProductDto productDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveOrEdit(null,productDto));
    }

}
