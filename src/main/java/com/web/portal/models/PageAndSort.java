package com.web.portal.models;

import lombok.Data;

@Data
public class PageAndSort {
    private String sort;
    private int currentPage;
    private int pageSize;
    private String find;

    public PageAndSort(){}

    public PageAndSort(String sort, int currentPage, int pageSize, String find){

        this.sort = sort;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.find = find;
    }

    public String getSort(){
        return sort;
    }

    public void setFind(String find) {
        this.find = find;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getFind() {
        return find;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
