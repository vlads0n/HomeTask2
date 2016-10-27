package app.com.example.android.hometask2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Влад on 26.10.2016.
 */
public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.ViewHolder> {

    private ArrayList<Student> students;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView studentName;
        public Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            this.studentName = (TextView) itemView.findViewById(R.id.name_student);
            this.button = (Button) itemView.findViewById(R.id.git_button);

            studentName.setOnClickListener(this);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            switch (v.getId()) {
                case R.id.name_student:
                    itemClick(v.getContext(), position);
                    break;
                case R.id.git_button:
                    buttonClick(v.getContext(), position);

            }
        }

        public void itemClick(Context context, int position) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(students.get(position).getAccount()));
            context.startActivity(intent);
        }

        public void buttonClick(Context context, int position) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(students.get(position).getGit()));
            context.startActivity(intent);
        }
    }

    public StudentRecyclerAdapter(ArrayList<Student> students) {
        this.students = students;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.studentName.setText(students.get(position).getNameOfStudent());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

}
