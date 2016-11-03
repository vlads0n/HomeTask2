package app.com.example.android.hometask2;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountGPlusFragment extends Fragment {


    public AccountGPlusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_account_gplus, container, false);

        Intent intent = getActivity().getIntent();
        FetchAccountTask fetchAccountTask = new FetchAccountTask();

        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            fetchAccountTask.execute(intent.getStringExtra(Intent.EXTRA_TEXT));
        }

        String[] studentInfo = null;
        try {
            studentInfo = fetchAccountTask.get();
        }
        catch (Exception e) {}

        if (studentInfo != null) {
            FetchImageTask fetchImageTask = new FetchImageTask(studentInfo[0]);
            fetchImageTask.execute();
            try {
                Bitmap bitmap = fetchImageTask.get();
                ImageView imageView = (ImageView) rootView.findViewById(R.id.student_account_image);
                imageView.setImageBitmap(bitmap);
            }
            catch (Exception e) {}

            TextView name = (TextView) rootView.findViewById(R.id.student_account_name);
            name.setText(studentInfo[1]);

            TextView surname = (TextView) rootView.findViewById(R.id.student_account_surname);
            surname.setText(studentInfo[2]);
        }
        return rootView;
    }

}