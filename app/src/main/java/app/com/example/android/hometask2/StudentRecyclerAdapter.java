package app.com.example.android.hometask2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Влад on 26.10.2016.
 */
public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.ViewHolder> {

    private Student[] dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.name_student);
        }
    }

    public StudentRecyclerAdapter(Student[] dataSet) {
        this.dataSet = dataSet;
    }

    public StudentRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentRecyclerAdapter.ViewHolder holder, int position) {
        holder.textView.setText(dataSet[position].getNameOfStudent());
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }
}
