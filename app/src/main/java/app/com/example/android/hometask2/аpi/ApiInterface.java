package app.com.example.android.hometask2.аpi;

import app.com.example.android.hometask2.model.StudentGitProfile;
import app.com.example.android.hometask2.model.StudentGoogleProfile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Влад on 18.11.2016.
 */
public interface ApiInterface {
    @GET("users/{user}")
    Call<StudentGitProfile> getStudentGitHubProfile(@Path("user") String user);

    @GET("plus/v1/people/{user}")
    Call<StudentGoogleProfile> getStudentGoogleProfile(@Path("user") String user, @Query("key") String key);
}
