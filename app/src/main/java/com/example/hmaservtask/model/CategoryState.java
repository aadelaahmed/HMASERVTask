package com.example.hmaservtask.model;

import java.util.List;

public class CategoryState {
    public enum Status {
        LOADING,
        SUCCESS,
        ERROR

    }

    private String message;
    private Status status;
    private List<Category> categoryList;

    public CategoryState(List<Category> categoryList, Status status, String message) {
        this.message = message;
        this.status = status;
        this.categoryList = categoryList;
    }

    public CategoryState(Status status, String message) {
        this.message = message;
        this.status = status;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
