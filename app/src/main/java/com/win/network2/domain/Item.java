package com.win.network2.domain;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * author：WangShuang
 * Date: 2015/12/29 10:08
 * email：m15046658245_1@163.com
 */
public class Item {
    private  long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    private  String userIcon;
    private  String username;
    private String content;
    private String image;

    public Item( JSONObject object)throws JSONException{

       if(! object.isNull("user")) {
           userIcon = object.getJSONObject("user").getString("icon");
           username = object.getJSONObject("user").getString("login");
           userId = object.getJSONObject("user").getLong("id");
       }
        content=object.getString("content");

       if( !object.isNull("image")) {
           image = object.getString("image");
       }
       }
    public Item(String userIcon, String username, String content, String image) {
        this.userIcon = userIcon;
        this.username = username;
        this.content = content;
        this.image = image;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
