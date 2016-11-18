package app.com.example.android.hometask2.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Влад on 18.11.2016.
 */
public class StudentGitProfile {
    @SerializedName("login")
    private String login;
    @SerializedName("name")
    private String name;
    @SerializedName("avatar_url")
    private String avatar;

    public StudentGitProfile(String login, String name, String avatar) {
        this.login = login;
        this.name = name;
        this.avatar = avatar;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }
}
