package app.com.example.android.hometask2.gplusProfile;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.broadcastReceiver.HeadsetReceiver;
import app.com.example.android.hometask2.broadcastReceiver.PowerReceiver;
import app.com.example.android.hometask2.model.StudentGoogleProfile;
import app.com.example.android.hometask2.аpi.ApiInterface;
import app.com.example.android.hometask2.аpi.GooglePlusApiClient;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountGPlusFragment extends Fragment {
    HeadsetReceiver headsetReceiver;
    PowerReceiver powerReceiver;
    private final static String API_KEY_GOOGLE_PLUS = "AIzaSyCanY1NXsBG4o5tmH9aGv5g04QQt-foH-o";

    public AccountGPlusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_account_gplus, container, false);

        Intent intent = getActivity().getIntent();

        Call<StudentGoogleProfile> call = null;
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.student_account_gplus_image);
        final TextView name = (TextView) rootView.findViewById(R.id.student_account_gplus_name);
        final TextView surname = (TextView) rootView.findViewById(R.id.student_account_gplus_surname);

        Uri uri = null;
        if (intent != null)
            uri = intent.getData();

        Pattern pattern = Pattern.compile("https://plus.google.com/u/0/\\d{21}");

        if (uri != null) {
            if (pattern.matcher(uri.toString()).matches()) {
                ApiInterface apiInterface = GooglePlusApiClient.getClient().create(ApiInterface.class);
                call = apiInterface.getStudentGoogleProfile(uri.getLastPathSegment(), API_KEY_GOOGLE_PLUS);
            }
            else {
                Toast.makeText(getActivity(), "Invalid link!!! Choose another browser, please.", Toast.LENGTH_LONG).show();
                getActivity().startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        }
        else if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            ApiInterface apiInterface = GooglePlusApiClient.getClient().create(ApiInterface.class);
            call = apiInterface.getStudentGoogleProfile(intent.getStringExtra(Intent.EXTRA_TEXT), API_KEY_GOOGLE_PLUS);
        }
        if (call != null) {
            Log.v("LOG:", call.request().url().toString());
            call.enqueue(new Callback<StudentGoogleProfile>() {
                @Override
                public void onResponse(Call<StudentGoogleProfile> call, Response<StudentGoogleProfile> response) {
                    StudentGoogleProfile studentGoogleProfile = response.body();
                    name.setText(studentGoogleProfile.getName());
                    surname.setText(studentGoogleProfile.getSurname());
                    Picasso.with(getContext()).load(studentGoogleProfile.getImageUrl()).into(imageView);
                }

                @Override
                public void onFailure(Call<StudentGoogleProfile> call, Throwable t) {
                }
            });
        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        headsetReceiver = new HeadsetReceiver();
        powerReceiver = new PowerReceiver();
        getActivity().registerReceiver(headsetReceiver, new IntentFilter("android.intent.action.HEADSET_PLUG"));
        getActivity().registerReceiver(powerReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(headsetReceiver);
        getActivity().unregisterReceiver(powerReceiver);
    }
}
