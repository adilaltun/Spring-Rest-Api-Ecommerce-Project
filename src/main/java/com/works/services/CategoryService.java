package com.works.services;

import com.works.entities.Category;
import com.works.repositories.CategoryRepository;

import com.works.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryService {

    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //category save
    public ResponseEntity save (Category category){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        Optional<Category> optionalCategory = categoryRepository.
                findByCategoryNameEqualsIgnoreCase(category.getCategoryName());
        if (optionalCategory.isPresent()){
            hm.put(REnum.status,false);
            hm.put(REnum.message,"This category is already occorred");
            return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
        }else {
            Category category1 = categoryRepository.save(category);
            hm.put(REnum.status,true);
            hm.put(REnum.result,category1);
            return new ResponseEntity(hm,HttpStatus.OK);
        }
    }

    //category delete
    public ResponseEntity delete(Integer categoryId){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try {
            categoryRepository.deleteById(categoryId);
            hm.put(REnum.status,true);
            hm.put(REnum.result,categoryId);
            return new ResponseEntity(hm,HttpStatus.OK);
        }catch (Exception ex){
            hm.put(REnum.status,false);
            hm.put(REnum.error,ex.getMessage());
            return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
        }
    }

    //category list
    public ResponseEntity list(){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        List<Category> categories = categoryRepository.findAll();
        hm.put(REnum.status,true);
        hm.put(REnum.result,categories);
        return new ResponseEntity(hm,HttpStatus.OK);
    }

    //category update
    public ResponseEntity update (Category category) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            Optional<Category> optionalCategory = categoryRepository.findById(category.getCategoryId());
            if (optionalCategory.isPresent()) {
                categoryRepository.saveAndFlush(category);
                hm.put(REnum.status,true);
                hm.put(REnum.message,"Update Successfully!");
                return new ResponseEntity(hm, HttpStatus.OK);
            } else {
                hm.put(REnum.status,false);
                hm.put(REnum.message,"Update Fail");
                return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            hm.put(REnum.status,false);
            hm.put(REnum.error,ex.getMessage());
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
    }

}
