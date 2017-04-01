package com.app.int_a.giantbombforandroid.main.model;

/**
 * Created by Anthony on 3/4/2017.
 */

public class Post {

    private final Integer userId;
    private final Integer id;
    private final String name;
    private final String body;

    public Post(Integer userId, Integer id, String name, String body){
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.body = body;
    }

    public Integer getUserId(){
        return userId;
    }

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getBody(){
        return body;
    }

}
