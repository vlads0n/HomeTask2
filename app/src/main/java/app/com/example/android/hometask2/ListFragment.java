package app.com.example.android.hometask2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_students_list, container, false);

        final ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Vladyslav Vynnyk", "https://github.com/vlads0n"));
        students.add(new Student("Michael Tyoply", "https://github.com/RedGeekPanda"));
        students.add(new Student("Bogdan Rybak", "https://github.com/BogdanRybak1996"));
        students.add(new Student("Ivan Leschenko", "https://github.com/ivleshch"));
        students.add(new Student("Pavlo Sakurov", "https://github.com/sakurov"));
        students.add(new Student("Ruslan Volovyk", "https://github.com/RuslanVolovyk"));
        students.add(new Student("Daria Kyrychenko", "https://github.com/dashakdsr"));
        students.add(new Student("Andriy Ryabko", "https://github.com/RyabkoAndrew"));
        students.add(new Student("Evgeniy Sytnyk", "https://github.com/YevheniiSytnyk"));
        students.add(new Student("Edgar Khimich", "https://github.com/lyfm"));
        students.add(new Student("Alyona Bochkaryouva", "https://github.com/HelenCool"));
        students.add(new Student("Ruslan Mygal", "https://github.com/rmigal"));
        students.add(new Student("Iryna Smalko", "https://github.com/IraSmalko"));
        students.add(new Student("Valeriy Gubskiy", "https://github.com/gvv-ua"));
        students.add(new Student("Evgen Zhdanov", "https://github.com/zhdanov-ek"));
        students.add(new Student("Ivan Sergienko", "https://github.com/dogfight81"));
        students.add(new Student("Igor Paharenko", "https://github.com/IhorPakharenko"));
        students.add(new Student("Oleksandr Storchak", "https://github.com/new15"));
        students.add(new Student("Mykola Pikhmanec", "https://github.com/NikPikhmanets"));
        students.add(new Student("Volodymyr Lymar", "https://github.com/VovanNec"));

        StudentAdapter studentAdapter = new StudentAdapter(getActivity(), students);

        ListView listView = (ListView) rootView.findViewById(R.id.students_list);
        listView.setAdapter(studentAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = students.get(position);


            }
        });
        return rootView;
    }

}
