package com.example.demo;

/**
 * Created by song on 2018/3/10.
 */
//分页信息
public class Page {
    private long resultNumber;//结果总数

    private long pageNumber;//页总数

    private int pageNow;//当前页

    public long getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(long resultNumber) {
        this.resultNumber = resultNumber;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public long getPageNumber() {
        return resultNumber/10+1;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Page(long resultNumber, int pageNow) {
        this.resultNumber = resultNumber;
        this.pageNow = pageNow;
    }

    public Page() {
    }
}
