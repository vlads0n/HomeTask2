package app.com.example.android.hometask2.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.gitProfile.AccountGitActivity;
import app.com.example.android.hometask2.gplusProfile.AccountGPlusActivity;
import app.com.example.android.hometask2.model.Student;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Влад on 26.10.2016.
 */
public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.ViewHolder> {

    private RealmResults<Student> students;
    private Realm realm;
    private int positionDeletedStudent;
    private Student deletedStudent;
    private String nameOfDeletedStudent;
    private String gitOfDeletedStudent;
    private String accountOfDeletedStudent;
    private View rootView;

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
            Intent intent = new Intent(context, AccountGPlusActivity.class).putExtra(Intent.EXTRA_TEXT, students.get(position).getAccount());
            context.startActivity(intent);
        }

        public void buttonClick(Context context, int position) {
            Intent intent = new Intent(context, AccountGitActivity.class).putExtra(Intent.EXTRA_TEXT, students.get(position).getGit());
            context.startActivity(intent);
        }
    }

    public StudentRecyclerAdapter(RealmResults<Student> students, View rootView, Realm realm) {
        this.students = students;
        this.rootView = rootView;
        this.realm = realm;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
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

    public void dismissStudent(final int position){
        deletedStudent = students.get(position);
        positionDeletedStudent = position;
        nameOfDeletedStudent = deletedStudent.getNameOfStudent();
        gitOfDeletedStudent = deletedStudent.getGit();
        accountOfDeletedStudent = deletedStudent.getAccount();
        realm.beginTransaction();
        students.deleteFromRealm(position);
        realm.commitTransaction();
        this.notifyItemRemoved(position);
        showSnackBar();
    }

    public void undoDismissStudent() {
        final Student student = new Student();
        student.setNameOfStudent(nameOfDeletedStudent);
        student.setGit(gitOfDeletedStudent);
        student.setAccount(accountOfDeletedStudent);
        realm.beginTransaction();
        realm.insertOrUpdate(student);
        realm.commitTransaction();
        this.notifyItemInserted(positionDeletedStudent);
    }

    public void showSnackBar() {
        Snackbar snackbar = Snackbar
                .make(rootView, "Student is deleted", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        undoDismissStudent();
                        Snackbar snackbar1 = Snackbar.make(rootView, "Student is restored!", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    }
                });
        snackbar.show();
    }
}
