
package com.newsfeed.model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class NewsItem {

    @SerializedName("NewsItemId")
    private String id;
    @SerializedName("HeadLine")
    private String headLine;
    @SerializedName("ByLine")
    private String byLine;
    @SerializedName("Agency")
    private String agency;
    @SerializedName("DateLine")
    private String dateLine;
    @SerializedName("WebURL")
    private String webURL;
    @SerializedName("Caption")
    private String caption;
    @SerializedName("Image")
    private Image image;
    @SerializedName("Keywords")
    private String keywords;
    @SerializedName("Story")
    private String story;
    @SerializedName("CommentCountUrl")
    private String commentCountUrl;
    @SerializedName("CommentCount")
    private int commentCount;
    @SerializedName("CommentFeedUrl")
    private String commentFeedUrl;
    @SerializedName("Related")
    private String related;

    public NewsItem(){
        id = "";
        headLine = "";
        byLine = "";
        agency = "";
        dateLine = "";
        webURL = "";
        image = new Image();
        keywords = "";
        story = "";
        commentCountUrl = "";
        commentCount = 0;
        commentFeedUrl = "";
        related = "";
    }

    public String getID() {
        return id;
    }
    public void setID(String id) {
        this.id= id;
    }
    public String getHeadLine() {
        return headLine;
    }
    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }
    public String getByLine() {
        return byLine;
    }
    public void setByLine(String byLine) {
        this.byLine = byLine;
    }
    public String getAgency() {
        return agency;
    }
    public void setAgency(String agency) {
        this.agency = agency;
    }
    public String getDateLine() {
        return dateLine;
    }
    public void setDateLine(String dateLine) {
        this.dateLine = dateLine;
    }
    public String getWebURL() {
        return webURL;
    }
    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }
    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {this.caption = caption;}
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {this.image = image;}
    public String getKeywords() {return this.keywords;}
    public void setKeywords(String keywords) {this.keywords = keywords;}
    public String getStory() {
        return story;
    }
    public void setStory(String story) {this.story = story;}
    public String getCommentCountUrl() {return commentCountUrl;}
    public void setCommentCountUrl(String commentCountUrl) {this.commentCountUrl = commentCountUrl;}
    public void setCommentCount(int commentCount){this.commentCount = commentCount;}
    public int getCommentCount(){return this.commentCount;}
    public String getCommentFeedUrl() {return commentFeedUrl;}
    public void setCommentFeedUrl(String commentFeedUrl) {
        this.commentFeedUrl = commentFeedUrl;
    }
    public String getRelated() {return related;}
    public void setRelated(String related) {this.related = related;}
}
