package com.works.services;

import com.works.entities.ProductImage;
import com.works.repositories.ProductImageRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    final ProductImageRepository productImageRepository;

    //image add
    public ResponseEntity add(ProductImage productImage) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            productImageRepository.save(productImage);
            hm.put(REnum.status, true);
            hm.put(REnum.result,productImage);
        } catch (Exception e) {
            hm.put(REnum.status, false);
            hm.put(REnum.message, "Error:"+e);
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    //image list
    public ResponseEntity list(Integer productId) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", productImageRepository.findByProductIdEquals(productId));
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    //image delete
    public ResponseEntity delete(Integer imageId) {
        Map<String, Object> hm = new LinkedHashMap<>();
        try {
            productImageRepository.deleteById(imageId);
            hm.put("status", true);
        }catch (Exception ex) {
            hm.put("status", false);
        }
        hm.put("result", imageId);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}