package com.xxx.bean;

public class Music {
    private String title;
    private String time;
    private String author;
    private String album_name;
    private int id;
    private int wid;

    public Music() {}

    public Music(String title, String time, String author, String album_name, int id, int wid) {
        this.title = title;
        this.time = time;
        this.author = author;
        this.album_name = album_name;
        this.id = id;
        this.wid = wid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }
}
