package com.web.portal.models;

import lombok.Data;

import java.util.List;

@Data
public class Pager {
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int pagesCount;
    private List<Game> games;

    PageAndSort pageAndSort;

    public Pager() {
    }

    public Pager(List<Game> games, boolean hasPreviousPage, boolean hasNextPage, int pagesCount, PageAndSort pageAndSort) {
        this.games = games;
        this.hasNextPage = hasNextPage;
        this.hasPreviousPage = hasPreviousPage;
        this.pagesCount = pagesCount;
        this.pageAndSort = pageAndSort;
    }

    public PageAndSort getPageAndSort() {
        return pageAndSort;
    }

    public void setPageAndSort(PageAndSort pageAndSort) {
        this.pageAndSort = pageAndSort;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
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

    public boolean isHasNextPage() {
        return hasNextPage;
    }
}
