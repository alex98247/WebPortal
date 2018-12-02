package com.web.portal.models;

import lombok.Data;

import java.util.List;

@Data
public class Pager {
    private boolean hasPreviousPage;
    private int currentPage;
    private boolean hasNextPage;
    private int pageSize;
    private int pagesCount;
    private List<Game> games;

    public Pager() {
    }

    public Pager(List<Game> games, boolean hasPreviousPage, int currentPage, boolean hasNextPage, int size, int pagesCount) {
        this.games = games;
        this.currentPage = currentPage;
        this.hasNextPage = hasNextPage;
        this.hasPreviousPage = hasPreviousPage;
        this.pageSize = size;
        this.pagesCount = pagesCount;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }
}
