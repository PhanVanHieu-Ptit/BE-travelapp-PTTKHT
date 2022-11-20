package com.thanhsang.travelapp.model.Food;

import java.util.List;

public class OrderFoodRequestModel {
    
    private OrderFoodModel order;
    private List<OrderFoodDetailModel> orderDetail;

    public OrderFoodRequestModel() {}
    
    public OrderFoodRequestModel(OrderFoodModel order, List<OrderFoodDetailModel> orderDetail) {
        this.order = order;
        this.orderDetail = orderDetail;
    }

    public void setOrder(OrderFoodModel order) {
        this.order = order;
    }

    public void setOrderDetail(List<OrderFoodDetailModel> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public OrderFoodModel getOrder() {
        return order;
    }

    public List<OrderFoodDetailModel> getOrderDetail() {
        return orderDetail;
    }
}
