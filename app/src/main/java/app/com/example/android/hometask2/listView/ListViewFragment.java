package app.com.example.android.hometask2.listView;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.model.Student;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {


    public ListViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);

        final ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Vladyslav Vynnyk", "https://github.com/vlads0n", "https://plus.google.com/u/0/117765348335292685488"));
        students.add(new Student("Michael Tyoply", "https://github.com/RedGeekPanda", "https://plus.google.com/u/0/110313151428733681846"));
        students.add(new Student("Bogdan Rybak", "https://github.com/BogdanRybak1996", "https://plus.google.com/u/0/103145064185261665176"));
        students.add(new Student("Ivan Leschenko", "https://github.com/ivleshch", "https://plus.google.com/u/0/111088051831122657934"));
        students.add(new Student("Pavlo Sakurov", "https://github.com/sakurov", "https://plus.google.com/u/0/108482088578879737406"));
        students.add(new Student("Ruslan Volovyk", "https://github.com/RuslanVolovyk", "https://plus.google.com/u/0/109719711261293841416"));
        students.add(new Student("Daria Kyrychenko", "https://github.com/dashakdsr", "https://plus.google.com/u/0/103130382244571139113"));
        students.add(new Student("Andriy Ryabko", "https://github.com/RyabkoAndrew", "https://plus.google.com/u/0/110288437168771810002"));
        students.add(new Student("Evgeniy Sytnyk", "https://github.com/YevheniiSytnyk", "https://plus.google.com/u/0/101427598085441575303"));
        students.add(new Student("Edgar Khimich", "https://github.com/lyfm", "https://plus.google.com/u/0/102197104589432395674"));
        students.add(new Student("Alyona Bochkaryouva", "https://github.com/HelenCool", "https://plus.google.com/u/0/107382407687723634701"));
        students.add(new Student("Ruslan Mygal", "https://github.com/rmigal", "https://plus.google.com/u/0/106331812587299981536"));
        students.add(new Student("Iryna Smalko", "https://github.com/IraSmalko", "https://plus.google.com/u/0/113994208318508685327"));
        students.add(new Student("Valeriy Gubskiy", "https://github.com/gvv-ua", "https://plus.google.com/u/0/107910188078571144657"));
        students.add(new Student("Evgen Zhdanov", "https://github.com/zhdanov-ek", "https://plus.google.com/u/0/113264746064942658029"));
        students.add(new Student("Ivan Sergienko", "https://github.com/dogfight81", "https://plus.google.com/u/0/111389859649705526831"));
        students.add(new Student("Igor Paharenko", "https://github.com/IhorPakharenko", "https://plus.google.com/u/0/108231952557339738781"));
        students.add(new Student("Oleksandr Storchak", "https://github.com/new15", "https://plus.google.com/u/0/106553086375805780685"));
        students.add(new Student("Mykola Pikhmanec", "https://github.com/NikPikhmanets", "https://plus.google.com/u/0/110087894894730430086"));
        students.add(new Student("Volodymyr Lymar", "https://github.com/VovanNec", "https://plus.google.com/u/0/109227554979939957830"));

        final StudentListAdapter studentListAdapter = new StudentListAdapter(getActivity(), students);

        final ListView listView = (ListView) rootView.findViewById(R.id.students_list);
        listView.setAdapter(studentListAdapter);

        listView.setOnTouchListener(new View.OnTouchListener() {
            float x = Float.NaN;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        x = motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_DOWN:
                        if (motionEvent.getX() - x < - 250 || motionEvent.getX() - x > 250) {
                            final Student deleteStudent = students.get(view.getVerticalScrollbarPosition());
                            students.remove(view.getVerticalScrollbarPosition());
                            studentListAdapter.notifyDataSetChanged();
                            Snackbar snackbar = Snackbar
                                    .make(rootView, "Student is deleted", Snackbar.LENGTH_LONG)
                                    .setAction("UNDO", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            students.add(view.getVerticalScrollbarPosition(), deleteStudent);
                                            studentListAdapter.notifyDataSetChanged();
                                            Snackbar snackbar1 = Snackbar.make(rootView, "Student is restored!", Snackbar.LENGTH_SHORT);
                                            snackbar1.show();
                                        }
                                    });
                            snackbar.show();
                            return true;
                        }
                }
                return false;
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = students.get(position);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(student.getAccount()));
                startActivity(intent);
                return true;
            }
        });
        return rootView;
    }

}
