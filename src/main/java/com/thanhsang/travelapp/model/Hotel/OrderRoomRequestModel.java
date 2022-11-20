package com.thanhsang.travelapp.model.Hotel;

import java.util.List;

public class OrderRoomRequestModel {
    private OrderRoomModel order;
    private List<OrderRoomDetailModel> orderDetail;

    public OrderRoomRequestModel() {}

    public OrderRoomRequestModel(OrderRoomModel order, List<OrderRoomDetailModel> orderDetail) {
        this.order = order;
        this.orderDetail = orderDetail;
    }

    public void setOrder(OrderRoomModel order) {
        this.order = order;
    }

    public void setOrderDetail(List<OrderRoomDetailModel> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public OrderRoomModel getOrder() {
        return order;
    }

    public List<OrderRoomDetailModel> getOrderDetail() {
        return orderDetail;
    };

}
