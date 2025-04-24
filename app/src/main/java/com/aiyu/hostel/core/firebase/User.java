package com.aiyu.hostel.core.firebase;

import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class User implements Serializable {
    private String name;
    private String email;
    private String profilePic;
    private String uid;
    private Long createdAt;

    public User() {
    }

    public User(String name, String email, String phone, String profilePic, String uid) {
        this.name = name;
        this.email = email;
        this.profilePic = profilePic;
        this.uid = uid;
        this.createdAt = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getCreatedAt() {
        return createdAt;
    }
}
