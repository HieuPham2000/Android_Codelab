package com.example.android.inbox;

public class ItemModel {
    private int avatarResource;
    private String name;
    private String title;
    private String content;
    private String time;
    private boolean star = false;

    public ItemModel() {

    }

    public ItemModel(int avatarResource, String name, String title, String content, String time) {
        this.avatarResource = avatarResource;
        this.name = name;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public int getAvatarResource() {
        return avatarResource;
    }

    public void setAvatarResource(int avatarResource) {
        this.avatarResource = avatarResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }
}
