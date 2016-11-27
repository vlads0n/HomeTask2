package app.com.example.android.hometask2.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by Влад on 18.11.2016.
 */
public class StudentGoogleProfile {
    @SerializedName("name")
    private HashMap<String, String> name;
    @SerializedName("image")
    private HashMap<String, String> image;


    public StudentGoogleProfile(HashMap<String, String> image, HashMap<String, String> name) {
        this.name = name;
        this.image = image;
    }

    public String getSurname() {
        return name.get("familyName");
    }

    public String getName() {
        return name.get("givenName");
    }

    public String getImageUrl() {
         return image.get("url");
    }
}
