package app.com.example.android.hometask2.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.util.FetchAccountGitTask;
import app.com.example.android.hometask2.util.FetchImageTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountGitFragment extends Fragment {


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

        String[] studentInfo = null;
        try {
            studentInfo = fetchAccountGitTask.get();
        }
        catch (Exception e) {}

        if (studentInfo != null) {
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
        return rootView;
    }

}
