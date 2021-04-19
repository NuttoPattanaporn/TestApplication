package com.example.searchtest.service.age;

import com.google.gson.annotations.SerializedName;

public class Age {
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("job")
    private String job;

    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }

    public String getAvatar(){
        return avatar;
    }

    public void setJob(String job){
        this.job = job;
    }

    public String getJob(){
        return job;
    }

    @Override
    public String toString() {
        return "Age{" +
                "createdAt='" + createdAt + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", avatar='" + avatar + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
