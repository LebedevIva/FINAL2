package com.example.springsecurityapplication.servises;

import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderServise {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServise(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

//    Получаем все заказы
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    //      Метод возвращает заказ по id
    public Order getOrderId(int id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElse(null);//Возвращаем optional, если в нём нет order то возвращаем null
    }

    //    Метод сохраняет объект, который пришёл с form
    @Transactional //Переопределяем transactional
    public void saveOrder(Order order){
        orderRepository.save(order);
    }

    //    Метод обновляет информацию о product
    @Transactional //Переопределяем transactional
    public  void updateOrder(int id, Order order){
        order.setId(id);
        orderRepository.save(order);
    }

    //    Метод получает товар по наименованию
    public Order getOrderFindByNumber(Order order){
        List<Order> order_db = orderRepository.findByNumber(order.getNumber());
        return (Order) order_db;
    }

}
