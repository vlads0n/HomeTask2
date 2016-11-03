package app.com.example.android.hometask2;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Влад on 01.11.2016.
 */
public class FetchAccountTask extends AsyncTask<String, Void, String[]> {
    private static final String KEY = "AIzaSyCanY1NXsBG4o5tmH9aGv5g04QQt-foH-o";
    private static final String ACCOUNT_BASE_URL = "https://www.googleapis.com/plus/v1/people/";
    private final String KEY_PARAM = "key";

    @Override
    protected String[] doInBackground(String... params) {
        Uri uri = Uri.parse(ACCOUNT_BASE_URL).buildUpon().appendPath(params[0]).appendQueryParameter(KEY_PARAM, KEY).build();
        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(uri.toString());

            Log.v("LOG:" , url.toString());

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
        for (String string : result)
            Log.v("LOG:", string);
        return result;
    }

    private String[] getAccountDataFromJSON(String accountJSONStr){
        final String OWM_IMAGE_LIST = "image";
        final String OWM_NAME_LIST = "name";
        final String OWM_SURNAME = "familyName";
        final String OWM_NAME = "givenName";
        String[] studentInfo = new String[3];

        try {
            JSONObject jsonObject = new JSONObject(accountJSONStr);
            JSONObject image = jsonObject.getJSONObject(OWM_IMAGE_LIST);
            String OWM_IMAGE_URL = "url";
            studentInfo[0] = image.getString(OWM_IMAGE_URL);

            JSONObject student = jsonObject.getJSONObject(OWM_NAME_LIST);
            studentInfo[1] = student.getString(OWM_NAME);
            studentInfo[2] = student.getString(OWM_SURNAME);
        } catch (JSONException e) {
        }
        return studentInfo;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);
    }
}
