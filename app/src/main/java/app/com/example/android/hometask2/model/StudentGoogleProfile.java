package app.com.example.android.hometask2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Влад on 18.11.2016.
 */
public class StudentGoogleProfile {
    @SerializedName("nickname")
    private String login;
    @SerializedName("name")
    private List<String> listName;
    private String surname;
    private String name;
    @SerializedName("image")
    private List<String> listImage;
    private String imageUrl;


    public StudentGoogleProfile(String login, List<String> listImage, List<String> listName) {
        this.login = login;
        this.listName = listName;
        this.surname = listName.get(0);
        this.name = listName.get(1);
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
