package com.qianwang.mvplogin.model;

/**
 * Created by sky on 2017/4/19.
 */

public class UserInfo{

    private String nickname;
    private int sex;
    private String headPic;  //头像
    private String sigId;
    private String userId;
    private String sdkAppId;
    private String sdkAccountType;
    private String token;

    public UserInfo() {
    }

    public UserInfo(String nickname, int sex, String headPic, String userId) {
        this.nickname = nickname;
        this.sex = sex;
        this.headPic = headPic;
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getSigId() {
        return sigId;
    }

    public void setSigId(String sigId) {
        this.sigId = sigId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSdkAppId() {
        return sdkAppId;
    }

    public void setSdkAppId(String sdkAppId) {
        this.sdkAppId = sdkAppId;
    }

    public String getSdkAccountType() {
        return sdkAccountType;
    }

    public void setSdkAccountType(String sdkAccountType) {
        this.sdkAccountType = sdkAccountType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
