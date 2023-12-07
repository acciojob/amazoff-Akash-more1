package com.driver;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    OrderRepository orderRepository = new OrderRepository();

    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    public void addPartner(String partner) {
        DeliveryPartner deliveryPartner=new DeliveryPartner(partner);

        orderRepository.addPartner(deliveryPartner);
    }

    public void assignOrder(String orderId, String partnerId) {
        orderRepository.assignOrder(orderId, partnerId);
    }

    public Order getByOrderId(String orderId) {
        return orderRepository.getByOrderId(orderId);
    }

    public DeliveryPartner getByPartnerId(String partnerId) {
        return orderRepository.getByPartnerId(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId) {
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return orderRepository.getOrdersByPartnerId(partnerId);
    }

    public List<String> getAllOrders() {

        return orderRepository.getAllOrders();
    }

    public int getCountOfUnassignedOrders() {
        return orderRepository.getCountOfUnassignedOrders();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time, partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public void deletePartnerById(String partnerId) {
        orderRepository.deletePartnerById(partnerId);

    }

    public void deleteOrderById(String orderId) {
        orderRepository.deleteOrderById(orderId);
    }
}
