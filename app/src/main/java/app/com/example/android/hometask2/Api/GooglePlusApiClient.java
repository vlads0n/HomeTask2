package app.com.example.android.hometask2.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Влад on 18.11.2016.
 */
public class GooglePlusApiClient {
    private final static String BASE_URL_GOOGLE_PLUS = "https://www.googleapis.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_GOOGLE_PLUS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }
}
