package app.com.example.android.hometask2.listView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.gitProfile.AccountGitActivity;
import app.com.example.android.hometask2.model.Student;
import io.realm.RealmResults;

/**
 * Created by Влад on 24.10.2016.
 */
public class StudentListAdapter extends ArrayAdapter<Student> {

    public StudentListAdapter(Context context, RealmResults<Student> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        final Student currentStudent = getItem(position);

        TextView textView = (TextView) listItemView.findViewById(R.id.name_student);
        textView.setText(currentStudent.getNameOfStudent());

        Button button = (Button) listItemView.findViewById(R.id.git_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AccountGitActivity.class).putExtra(Intent.EXTRA_TEXT, currentStudent.getGit());
                getContext().startActivity(intent);
            }
        });
        return listItemView;
    }
}
