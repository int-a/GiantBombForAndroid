package com.app.int_a.giantbombforandroid.main.model;

/**
 * Created by Anthony on 3/18/2017.
 */

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("api_detail_url")
    @Expose
    private String apiDetailUrl;
    @SerializedName("deck")
    @Expose
    private String deck;
    @SerializedName("hd_url")
    @Expose
    private String hdUrl;
    @SerializedName("high_url")
    @Expose
    private String highUrl;
    @SerializedName("low_url")
    @Expose
    private String lowUrl;
    @SerializedName("embed_player")
    @Expose
    private String embedPlayer;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("length_seconds")
    @Expose
    private Integer lengthSeconds;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("publish_date")
    @Expose
    private String publishDate;
    @SerializedName("site_detail_url")
    @Expose
    private String siteDetailUrl;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("video_type")
    @Expose
    private String videoType;
    @SerializedName("video_show")
    @Expose
    private VideoShow videoShow;
    @SerializedName("video_categories")
    @Expose
    private List<VideoCategory> videoCategories = null;
    @SerializedName("youtube_id")
    @Expose
    private Object youtubeId;

    public Result(){
        videoCategories = new ArrayList<VideoCategory>();
    }

    public String getApiDetailUrl() {
        return apiDetailUrl;
    }

    public void setApiDetailUrl(String apiDetailUrl) {
        this.apiDetailUrl = apiDetailUrl;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }

    public String getHighUrl() {
        return highUrl;
    }

    public void setHighUrl(String highUrl) {
        this.highUrl = highUrl;
    }

    public String getLowUrl() {
        return lowUrl;
    }

    public void setLowUrl(String lowUrl) {
        this.lowUrl = lowUrl;
    }

    public String getEmbedPlayer() {
        return embedPlayer;
    }

    public void setEmbedPlayer(String embedPlayer) {
        this.embedPlayer = embedPlayer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLengthSeconds() {
        return lengthSeconds;
    }

    public void setLengthSeconds(Integer lengthSeconds) {
        this.lengthSeconds = lengthSeconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getSiteDetailUrl() {
        return siteDetailUrl;
    }

    public void setSiteDetailUrl(String siteDetailUrl) {
        this.siteDetailUrl = siteDetailUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public VideoShow getVideoShow() {
        return videoShow;
    }

    public void setVideoShow(VideoShow videoShow) {
        this.videoShow = videoShow;
    }

    public List<VideoCategory> getVideoCategories() {
        return videoCategories;
    }

    public void setVideoCategories(List<VideoCategory> videoCategories) {
        this.videoCategories = videoCategories;
    }

    public Object getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(Object youtubeId) {
        this.youtubeId = youtubeId;
    }

}