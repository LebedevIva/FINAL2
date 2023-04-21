package com.example.springsecurityapplication.util;

import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.servises.ProductServise;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    private final ProductServise productServise;

    public ProductValidator(ProductServise productServise) {
        this.productServise = productServise;
    }


//    Метод указывает, для какой модели предназначен валидатор
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        if(productServise.getProductFindByTitle(product) != null){
            errors.rejectValue("title","","Данное наименование товара уже используется");
        }
    }
}
