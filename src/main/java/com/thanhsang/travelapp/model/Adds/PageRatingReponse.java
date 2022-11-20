package com.thanhsang.travelapp.model.Adds;

import java.util.List;
import java.util.Map;

public class PageRatingReponse {
    
    private List<Map<String, RatingReponse>> rating;
    private int elementsOfPage;
    private int size;
    private int currentPage;
    private int totalElements;
    private int totalPages;

    public PageRatingReponse() {}

    public PageRatingReponse(List<Map<String, RatingReponse>> rating, int elementsOfPage, int size, int currentPage,
            int totalElements, int totalPages) {
        this.rating = rating;
        this.elementsOfPage = elementsOfPage;
        this.size = size;
        this.currentPage = currentPage;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public void setRating(List<Map<String, RatingReponse>> rating) {
        this.rating = rating;
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

    public List<Map<String, RatingReponse>> getRating() {
        return rating;
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
