package app.com.example.android.hometask2.recyclerView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.model.Student;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {


    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);

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

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new StudentRecyclerAdapter(students);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

}
