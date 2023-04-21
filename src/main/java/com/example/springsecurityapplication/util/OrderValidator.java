package com.example.springsecurityapplication.util;

import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.servises.OrderServise;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class OrderValidator implements Validator {

    private  final OrderServise orderServise;

    public OrderValidator(OrderServise orderServise) {
        this.orderServise = orderServise;
    }

    //    Метод указывает, для какой модели предназначен валидатор
    @Override
    public boolean supports(Class<?> clazz) {
        return Order.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Order order = (Order) target;

        if(orderServise.getOrderFindByNumber(order) != null){
            errors.rejectValue("number","","Такого заказа не существует");
        }
    }
}
