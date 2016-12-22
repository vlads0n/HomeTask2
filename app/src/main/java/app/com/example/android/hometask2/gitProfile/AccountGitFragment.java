package app.com.example.android.hometask2.gitProfile;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.broadcastReceiver.HeadsetReceiver;
import app.com.example.android.hometask2.broadcastReceiver.PowerReceiver;
import app.com.example.android.hometask2.model.StudentGitProfile;
import app.com.example.android.hometask2.аpi.ApiInterface;
import app.com.example.android.hometask2.аpi.GitHubApiClient;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountGitFragment extends Fragment {
    HeadsetReceiver headsetReceiver;
    PowerReceiver powerReceiver;
    boolean invalidLink;

    public AccountGitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_account_git, container, false);

        Intent intent = getActivity().getIntent();

        Call<StudentGitProfile> call = null;
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.student_account_git_image);
        final TextView name = (TextView) rootView.findViewById(R.id.student_account_git_name);
        final TextView login = (TextView) rootView.findViewById(R.id.student_account_git_login);

        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            ApiInterface apiInterface = GitHubApiClient.getClient().create(ApiInterface.class);
            call = apiInterface.getStudentGitHubProfile(intent.getStringExtra(Intent.EXTRA_TEXT));
        }

        call.enqueue(new Callback<StudentGitProfile>() {
            @Override
            public void onResponse(Call<StudentGitProfile> call, Response<StudentGitProfile> response) {
                StudentGitProfile studentGitProfile = response.body();
                name.setText(studentGitProfile.getName());
                login.setText(studentGitProfile.getLogin());
                Picasso.with(getContext()).load(studentGitProfile.getAvatar()).into(imageView);
            }

            @Override
            public void onFailure(Call<StudentGitProfile> call, Throwable t) {

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
