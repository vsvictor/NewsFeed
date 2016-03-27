
package com.newsfeed.model;

import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("TotalPages")
    private Integer totalPages;
    @SerializedName("PageNo")
    private Integer pageNo;
    @SerializedName("PerPage")
    private Integer perPage;
    @SerializedName("WebURL")
    private String webURL;

    public Pagination(){
        totalPages = -1;
        pageNo = 0;
        perPage = 0;
        webURL = "";
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    public int getPerPage() {
        return perPage;
    }
    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
    public String getWebURL() {
        return webURL;
    }
    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("TotalPages:");
        sb.append(totalPages);
        sb.append("PageNo:");
        sb.append(pageNo);
        sb.append("PerPage:");
        sb.append(perPage);
        sb.append("WebURL:");
        sb.append(webURL);
        return sb.toString();
    }
}
