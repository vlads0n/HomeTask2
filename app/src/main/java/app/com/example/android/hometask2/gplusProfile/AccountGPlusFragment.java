package app.com.example.android.hometask2.gplusProfile;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import app.com.example.android.hometask2.ApiClient;
import app.com.example.android.hometask2.ApiInterface;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.broadcastReceiver.HeadsetReceiver;
import app.com.example.android.hometask2.broadcastReceiver.PowerReceiver;
import app.com.example.android.hometask2.model.StudentGoogleProfile;
import app.com.example.android.hometask2.util.FetchImageTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
//        FetchAccountGoogleTask fetchAccountGoogleTask = new FetchAccountGoogleTask();
//
//        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT))
//            fetchAccountGoogleTask.execute(intent.getStringExtra(Intent.EXTRA_TEXT));
//        else if (intent.getData().getHost().equals("plus.google.com"))
//            fetchAccountGoogleTask.execute(intent.getData().getLastPathSegment());
//
//        String[] studentInfo = null;
//        try {
//            studentInfo = fetchAccountGoogleTask.get();
//        }
//        catch (Exception e) {}

//        if (studentInfo != null && studentInfo[0] != null) {
//            if (!studentInfo[0].equals("null")) {
//                FetchImageTask fetchImageTask = new FetchImageTask(studentInfo[0]);
//                fetchImageTask.execute();
//                try {
//                    Bitmap bitmap = fetchImageTask.get();
//                    ImageView imageView = (ImageView) rootView.findViewById(R.id.student_account_gplus_image);
//                    imageView.setImageBitmap(bitmap);
//                } catch (Exception e) {
//                }
//            }
//
//            TextView name = (TextView) rootView.findViewById(R.id.student_account_gplus_name);
//            if (!studentInfo[1].equals("null"))
//                name.setText(studentInfo[1]);
//            else
//                name.setText("");
//
//            TextView surname = (TextView) rootView.findViewById(R.id.student_account_gplus_surname);
//            if (!studentInfo[2].equals("null"))
//                surname.setText(studentInfo[2]);
//            else
//                surname.setText("");
//        }
//        else {
//            Intent intent1 = new Intent(Intent.ACTION_VIEW, intent.getData());
//            Toast.makeText(getContext(), "Invalid link!!! Choose another browser.", Toast.LENGTH_LONG).show();
//            startActivity(intent1);
//        }

        Call<StudentGoogleProfile> call = null;
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.student_account_gplus_image);
        final TextView name = (TextView) rootView.findViewById(R.id.student_account_gplus_name);
        final TextView surname = (TextView) rootView.findViewById(R.id.student_account_gplus_surname);

        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            ApiInterface apiInterface = new ApiClient(ApiClient.GOOGLE_PLUS).getClient().create(ApiInterface.class);
            call = apiInterface.getStudentGoogleProfile(intent.getStringExtra(Intent.EXTRA_TEXT), API_KEY_GOOGLE_PLUS);
        }

        call.enqueue(new Callback<StudentGoogleProfile>() {
            @Override
            public void onResponse(Call<StudentGoogleProfile> call, Response<StudentGoogleProfile> response) {
                name.setText(response.body().getName());
                surname.setText(response.body().getSurname());
                FetchImageTask fetchImageTask = new FetchImageTask(response.body().getImageUrl());
                fetchImageTask.execute();
                try {
                    imageView.setImageBitmap(fetchImageTask.get());
                } catch (Exception e) {}
            }

            @Override
            public void onFailure(Call<StudentGoogleProfile> call, Throwable t) {

            }
        });

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
