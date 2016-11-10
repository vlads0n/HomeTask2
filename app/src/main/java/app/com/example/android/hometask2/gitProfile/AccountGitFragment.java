package app.com.example.android.hometask2.gitProfile;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.broadcastReceiver.HeadsetReceiver;
import app.com.example.android.hometask2.broadcastReceiver.PowerReceiver;
import app.com.example.android.hometask2.util.FetchAccountGitTask;
import app.com.example.android.hometask2.util.FetchImageTask;


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
        FetchAccountGitTask fetchAccountGitTask = new FetchAccountGitTask();

        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT))
            fetchAccountGitTask.execute(intent.getStringExtra(Intent.EXTRA_TEXT));
        else if (intent.getData().getHost().equals("github.com")) {
            if (intent.getData().getPathSegments().size() == 1)
                fetchAccountGitTask.execute(intent.getData().getLastPathSegment());
            else {
                fetchAccountGitTask.execute(intent.getData().getLastPathSegment());
                invalidLink = true;
            }
        }

        String[] studentInfo = null;
        try {
            studentInfo = fetchAccountGitTask.get();
        }
        catch (Exception e) {}

        if (studentInfo != null && !invalidLink) {
            if (!studentInfo[0].equals("null")) {
                FetchImageTask fetchImageTask = new FetchImageTask(studentInfo[0]);
                fetchImageTask.execute();
                try {
                    Bitmap bitmap = fetchImageTask.get();
                    ImageView imageView = (ImageView) rootView.findViewById(R.id.student_account_git_image);
                    imageView.setImageBitmap(bitmap);
                } catch (Exception e) {
                }
            }

            TextView name = (TextView) rootView.findViewById(R.id.student_account_git_name);
            if (!studentInfo[1].equals("null"))
                name.setText(studentInfo[1]);
            else
                name.setText("");

            TextView login = (TextView) rootView.findViewById(R.id.student_account_git_login);
            if (!studentInfo[2].equals("null"))
                login.setText(studentInfo[2]);
            else
                login.setText("");
        }
        else {
            Intent intent1 = new Intent(Intent.ACTION_VIEW, intent.getData());
            Toast.makeText(getContext(), "Invalid link!!! Choose another browser.", Toast.LENGTH_LONG).show();
            startActivity(intent1);
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
