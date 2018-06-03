package com.example.demo;

/**
 * Created by song on 2018/3/9.
 */
//内容
public class ShowPage {
    private String headline;

    private String content;

    private  String time;

    private String url;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ShowPage(String headline, String content, String time, String url) {
        this.headline = headline;
        this.content = content;
        this.time = time;
        this.url = url;
    }

    public ShowPage() {
    }
}
