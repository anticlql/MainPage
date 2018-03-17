package com.example.mainpage.beans;

/**
 * Created by Administrator on 2017/11/23 0023.
 */

public class Content {
    private String text;
    private int imageId;

    public Content(String text, int imageId){
        this.text=text;
        this.imageId=imageId;
    }
    public String getText(){
        return text;
    }
    public int getImageId(){
        return imageId;
    }
}
