package app.com.example.android.hometask2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Влад on 18.11.2016.
 */
public class ApiClient {
    private final static String BASE_URL_GIT_HUB = "https://api.github.com/";
    private final static String BASE_URL_GOOGLE_PLUS = "https://www.googleapis.com/plus/";
    private static Retrofit retrofit = null;
    public final static String GOOGLE_PLUS = "google plus";
    public final static String GIT_HUB = "git hub";
    private String socialWeb;

    public ApiClient(String socialWeb) {
        this.socialWeb = socialWeb;
    }

    public Retrofit getClient() {
        switch (socialWeb) {
            case GOOGLE_PLUS: {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL_GOOGLE_PLUS)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
                return retrofit;
            }
            case GIT_HUB: {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL_GIT_HUB)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
                return retrofit;
            }
            default:
                return null;
        }
    }
}
