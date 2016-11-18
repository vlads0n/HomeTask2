package app.com.example.android.hometask2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Влад on 18.11.2016.
 */
public class StudentGoogleProfile {
    @SerializedName("nickname")
    private String login;
    @SerializedName("familyName")
    private String surname;
    @SerializedName("givenName")
    private String name;
    @SerializedName("image")
    private List<String> listImage;
    private String imageUrl;

    public StudentGoogleProfile(String login, String surname, String name, List<String> listImage) {
        this.login = login;
        this.surname = surname;
        this.name = name;
        this.listImage = listImage;
        this.imageUrl = listImage.get(0);
    }

    public String getLogin() {
        return login;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
