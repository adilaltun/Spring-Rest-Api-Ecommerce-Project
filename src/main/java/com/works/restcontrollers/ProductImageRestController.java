package com.works.restcontrollers;

import com.works.entities.ProductImage;
import com.works.services.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ProductImageRestController {

    final ProductImageService productImageServiceService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody ProductImage productImage) {
        return productImageServiceService.add(productImage);
    }

    @GetMapping("/list/{productId}")
    public ResponseEntity list(@PathVariable Integer productId) {
        return productImageServiceService.list(productId);
    }

    @GetMapping("/delete/{imageId}")
    public ResponseEntity delete(@PathVariable Integer imageId) {
        return productImageServiceService.delete(imageId);
    }

}