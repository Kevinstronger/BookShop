package com.test.utils;

import com.test.entity.Book;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public class Page {
    private List<Book> records;
    private int pageNum; //当前页码
    private int pageSize=3;
    private int totalRecords;//总记录数
    private int totalPages;//总页数
    private int startIndex;//每页开始的索引

    private String url;

    public Page(int pageNum, int totalRecords){
        this.pageNum = pageNum;
        this.totalRecords = totalRecords;
        totalPages = totalRecords%pageSize == 0 ? totalRecords/pageSize : (totalRecords/pageSize + 1);
        //计算每页开始的索引
        startIndex = (pageNum - 1) * pageSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Book> getRecords() {
        return records;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setRecords(List<Book> records) {
        this.records = records;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }


}
