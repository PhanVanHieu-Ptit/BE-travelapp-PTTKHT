package com.thanhsang.travelapp.model.Adds;

import java.util.List;
import java.util.Map;

public class PageOrderHistoryReponse {
    
    private List<Map<Integer, OrderHistoryModel>> orders;
    private int elementsOfPage;
    private int size;
    private int currentPage;
    private int totalElements;
    private int totalPages;

    public PageOrderHistoryReponse() {}
    
    public PageOrderHistoryReponse(List<Map<Integer, OrderHistoryModel>> orders, int elementsOfPage, int size,
            int currentPage, int totalElements, int totalPages) {
        this.orders = orders;
        this.elementsOfPage = elementsOfPage;
        this.size = size;
        this.currentPage = currentPage;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public void setOrders(List<Map<Integer, OrderHistoryModel>> orders) {
        this.orders = orders;
    }

    public void setElementsOfPage(int elementsOfPage) {
        this.elementsOfPage = elementsOfPage;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Map<Integer, OrderHistoryModel>> getOrders() {
        return orders;
    }

    public int getElementsOfPage() {
        return elementsOfPage;
    }

    public int getSize() {
        return size;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }


}
