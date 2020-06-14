package com.example.productreview.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Review {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("reviewerName")
    @Expose
    private String reviewerName;
    @SerializedName("postTime")
    @Expose
    private String postTime;
    @SerializedName("readLink")
    @Expose
    private String readLink;
    @SerializedName("reviewSystem")
    @Expose
    private String reviewSystem;
    @SerializedName("pluses")
    @Expose
    private List<String> pluses;
    @SerializedName("minuses")
    @Expose
    private List<String> minuses;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("createTime")
    @Expose
    private String createTime;
    @SerializedName("lastModifyTime")
    @Expose
    private String lastModifyTime;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getReadLink() {
        return readLink;
    }

    public void setReadLink(String readLink) {
        this.readLink = readLink;
    }

    public String getReviewSystem() {
        return reviewSystem;
    }

    public void setReviewSystem(String reviewSystem) {
        this.reviewSystem = reviewSystem;
    }

    public List<String> getPluses() {
        return pluses;
    }

    public void setPluses(List<String> pluses) {
        this.pluses = pluses;
    }

    public List<String> getMinuses() {
        return minuses;
    }

    public void setMinuses(List<String> minuses) {
        this.minuses = minuses;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
