package com.story.hacker.modules.news.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "story")
public class Story {

    @SerializedName("by")
    private String by;

    @SerializedName("descendants")
    private Integer descendants;

    @PrimaryKey
    @SerializedName("id")
    private Integer id;

    @SerializedName("kids")
    private List<Integer> kids = null;

    @SerializedName("score")
    private Integer score;

    @SerializedName("time")
    private Integer time;

    @SerializedName("title")
    private String title;

    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;

    private int read;

    public String getBy() {
        return by;
    }

    public Integer getDescendants() {
        return descendants;
    }

    public Integer getId() {
        return id;
    }

    public List<Integer> getKids() {
        return kids;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public int getRead() {
        return read;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public void setDescendants(Integer descendants) {
        this.descendants = descendants;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setKids(List<Integer> kids) {
        this.kids = kids;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRead(int read) {
        this.read = read;
    }
}
