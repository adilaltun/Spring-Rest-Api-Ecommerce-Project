package com.works.restcontrollers;

import com.works.entities.Category;
import com.works.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryRestController {

    final CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity save (@RequestBody Category category){
        return categoryService.save(category);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity delete (@PathVariable Integer categoryId){
        return categoryService.delete(categoryId);
    }

    @GetMapping("/list")
    public ResponseEntity list (){
        return categoryService.list();
    }

    @PutMapping("/update")
    public ResponseEntity update (@RequestBody Category category){
        return categoryService.update(category);
    }

}
