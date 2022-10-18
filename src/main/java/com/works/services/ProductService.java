package com.works.services;

import com.works.entities.JoinProCat;
import com.works.entities.JoinProCatImage;
import com.works.entities.Product;
import com.works.repositories.CategoryRepository;
import com.works.repositories.JoinProCatImageRepository;
import com.works.repositories.JoinProCatRepository;
import com.works.repositories.ProductRepository;
import com.works.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService{

    final ProductRepository productRepository;
    final JoinProCatImageRepository joinProCatImageRepository;

    final JoinProCatRepository  joinProCatRepository;
    final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, JoinProCatImageRepository joinProCatImageRepository, JoinProCatRepository joinProCatRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.joinProCatImageRepository = joinProCatImageRepository;
        this.joinProCatRepository = joinProCatRepository;
        this.categoryRepository = categoryRepository;
    }

    //product save
    public ResponseEntity save (Product product){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        Optional<Product> optionalProduct = productRepository.findByProductNameEqualsIgnoreCase(product.getProductName());
        if (optionalProduct.isPresent()){
            hm.put(REnum.status,false);
            hm.put(REnum.message,"This product is already valid");
            return new ResponseEntity<>(hm,HttpStatus.BAD_REQUEST);
        }else {
            hm.put(REnum.status,true);
            hm.put(REnum.result,productRepository.save(product));
            return new ResponseEntity(hm,HttpStatus.OK);
        }
    }

    //product list
    public ResponseEntity list (){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        List<JoinProCat>  productList = joinProCatRepository.getProducts();
        hm.put(REnum.status,true);
        hm.put(REnum.result,productList);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    //product delete
    public ResponseEntity delete (Integer productId){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try {
            productRepository.deleteById(productId);
            hm.put(REnum.status,true);
            hm.put(REnum.message,"Delete Success");
            return new ResponseEntity(hm,HttpStatus.OK);
        }catch (Exception ex){
            hm.put(REnum.status,false);
            hm.put(REnum.error,ex.getMessage());
            return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
        }
    }

    //product update
    public ResponseEntity update (Product product){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try{
            Optional<Product> optionalProduct = productRepository.findById(product.getProductId());
            if (optionalProduct.isPresent()){
                Product product1 = productRepository.saveAndFlush(product);
                hm.put(REnum.status,true);
                hm.put(REnum.result,product1);
                return new ResponseEntity(hm,HttpStatus.OK);
            }else {
                hm.put(REnum.status,false);
                hm.put(REnum.message,"Update Fail");
                return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){

            hm.put(REnum.error,ex.getMessage());
            return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
        }
    }

    //Product Image List
    public ResponseEntity getImagesByProduct (){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        List<JoinProCatImage> imagesByProduct = joinProCatImageRepository.getImagesByProduct();
        hm.put(REnum.status,true);
        hm.put(REnum.result,imagesByProduct);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    //Find product's with product id
    public ResponseEntity findImageWithProductId(Integer productId){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        List<JoinProCatImage> productWithProductId = joinProCatImageRepository.findImageWithProductId(productId);
        hm.put(REnum.status,true);
        hm.put(REnum.result,productWithProductId);
        return new ResponseEntity(hm,HttpStatus.OK);
    }

    //Find product's with category id
    public ResponseEntity findProductWithCategory(Integer categoryId){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        List<JoinProCatImage> productWithCategory = joinProCatImageRepository.findProductWithCategory(categoryId);
        hm.put(REnum.status,true);
        hm.put(REnum.result,productWithCategory);
        return new ResponseEntity(hm,HttpStatus.OK);
    }

    //Find product with searching the words
    public ResponseEntity searchProduct (String q){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        List<JoinProCat> searchProductName = joinProCatRepository.searchProduct(q);
        if (searchProductName.size()>0){
            hm.put(REnum.status, true);
            hm.put(REnum.result, searchProductName);
            return new ResponseEntity(hm, HttpStatus.OK);
        }else {
            hm.put(REnum.status,false);
            hm.put(REnum.result,"No product contains with this request");
            return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity findProductById(Integer productId){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        Optional<JoinProCat> optionalJoinProCat = joinProCatRepository.findProductById(productId);
        if (optionalJoinProCat.isPresent()){
          hm.put(REnum.status,true);
          hm.put(REnum.result,optionalJoinProCat);
          return new ResponseEntity(hm, HttpStatus.OK);
        }else {
            hm.put(REnum.status,false);
            hm.put(REnum.message,"There is no product with this product id.");
            return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
        }

    }

}
