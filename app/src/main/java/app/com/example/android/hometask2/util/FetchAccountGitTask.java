package app.com.example.android.hometask2.util;

import android.net.Uri;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Влад on 03.11.2016.
 */
public class FetchAccountGitTask extends AsyncTask<String, Void, String[]> {
    private static final String ACCOUNT_BASE_URL = "https://api.github.com/users/";

    @Override
    protected String[] doInBackground(String... params) {
        Uri uri = Uri.parse(ACCOUNT_BASE_URL).buildUpon().appendPath(params[0]).build();
        StringBuilder stringBuilder = new StringBuilder();

        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(uri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

        }
        catch (Exception e) {}
        finally {
            urlConnection.disconnect();
        }

        String[] result = getAccountDataFromJSON(stringBuilder.toString());

        return result;
    }

    private String[] getAccountDataFromJSON(String accountJSONStr){
        final String OWM_IMAGE = "avatar_url";
        final String OWM_NAME = "name";
        final String OWM_LOGIN = "login";
        String[] studentInfo = new String[3];

        try {
            JSONObject jsonObject = new JSONObject(accountJSONStr);
            studentInfo[0] = jsonObject.getString(OWM_IMAGE);
            studentInfo[1] = jsonObject.getString(OWM_NAME);
            studentInfo[2] = jsonObject.getString(OWM_LOGIN);
        } catch (JSONException e) {
        }
        return studentInfo;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);
    }
}
