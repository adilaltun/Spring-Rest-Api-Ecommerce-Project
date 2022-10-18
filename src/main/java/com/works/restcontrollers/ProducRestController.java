package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProducRestController {

    final ProductService productService;

    public ProducRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity save (@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping("/list")
    public ResponseEntity list (){
        return productService.list();
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity delete (@PathVariable Integer productId){
        return productService.delete(productId);
    }

    @PutMapping("/update")
    public ResponseEntity update (@RequestBody Product product ){
        return productService.update(product);
    }

    @GetMapping("/getImagesByProduct")
    public ResponseEntity productImageList(){
        return productService.getImagesByProduct();
    }


    @GetMapping("/findImageWithProductId/{productId}")
    public ResponseEntity findProductWithProductId(@PathVariable Integer productId){
        return productService.findImageWithProductId(productId);
    }

    @GetMapping("/findProductWithCategory/{categoryId}")
    public ResponseEntity findProductWithCategory(@PathVariable Integer categoryId){
        return productService.findProductWithCategory(categoryId);
    }

    @GetMapping("/searchProduct")
    public ResponseEntity searchWithProductName(@RequestParam String q){
        return productService.searchProduct(q);
    }

    @GetMapping("/findProductById/{productId}")
    public ResponseEntity findProductById(@PathVariable Integer productId){
        return productService.findProductById(productId);
    }


}
