package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    HashMap<String, Order> orderRepo;
    HashMap<String, DeliveryPartner> deliveryPartnerRepo;
    HashMap<String, List<String>> partnerVsOrderRepo;
    HashMap<String, String> orderVsPartner;

    public OrderRepository() {
        orderRepo=new HashMap<>();
        deliveryPartnerRepo=new HashMap<>();
        partnerVsOrderRepo=new HashMap<>();
        orderVsPartner=new HashMap<>();
    }

    public void addOrder(Order order){
        orderRepo.put(order.getId(),order);

    }

    public  void addPartner(DeliveryPartner partner){
        deliveryPartnerRepo.put(partner.getId(), partner);
        partnerVsOrderRepo.put(partner.getId(),new ArrayList<>());
    }

    public void assignOrder(String orderId, String partnerId){
        partnerVsOrderRepo.get(partnerId).add(orderId);
        orderVsPartner.put(orderId,partnerId);
    }

    public Order getByOrderId(String orderId){
        return orderRepo.get(orderId);
    }

    public DeliveryPartner getByPartnerId(String partnerId){
        return deliveryPartnerRepo.get(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId){
        return partnerVsOrderRepo.get(partnerId).size();
    }

    public List<String> getOrdersByPartnerId(String partnerId){
        return partnerVsOrderRepo.get(partnerId);
    }

    public List<String> getAllOrders(){

        List<String > list=new ArrayList<>();

        for(String ele: orderRepo.keySet()){
            list.add(ele);
        }
        return list;
    }

    public int getCountOfUnassignedOrders (){
        int ct=0;

        for(String ele: orderRepo.keySet()){
            if(orderVsPartner.containsKey(ele)==false){
                ct++;
            }
        }

        return ct;
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
        int hh=Integer.parseInt(time.substring(0,2));
        int mm=Integer.parseInt(time.substring(3));
        int deliveryTime=(hh*60)+mm;

        int ct=0;

        for(String ele: partnerVsOrderRepo.get(partnerId)){
            if(orderRepo.get(ele).getDeliveryTime()>deliveryTime){
                ct++;
            }
        }

        return ct;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        String str="";
        int max=Integer.MIN_VALUE;

        for(String ele: partnerVsOrderRepo.get(partnerId)){
            if(orderRepo.get(ele).getDeliveryTime()>max){
                max=orderRepo.get(ele).getDeliveryTime();
                str=ele;
            }
        }
        return orderRepo.get(str).getTime();
    }

    public  void deletePartnerById(String partnerId){
        DeliveryPartner temp=deliveryPartnerRepo.get(partnerId);
        deliveryPartnerRepo.remove(partnerId);
        List<String> list= partnerVsOrderRepo.get(partnerId);

        partnerVsOrderRepo.remove(partnerId);

        for(String ele: orderVsPartner.keySet()){
            if(list.contains(ele)==true){
                orderVsPartner.remove(ele);
            }
        }

    }

    public void deleteOrderById(String orderId){
        orderRepo.remove(orderId);
        partnerVsOrderRepo.get(orderVsPartner.get(orderId)).remove(orderId);
        orderVsPartner.remove(orderId);
    }
}
