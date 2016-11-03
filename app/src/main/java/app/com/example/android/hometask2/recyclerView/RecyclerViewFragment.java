package app.com.example.android.hometask2.recyclerView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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

    View rootView;
    RecyclerView.Adapter adapter;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        final ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Vladyslav Vynnyk", "vlads0n", "117765348335292685488"));
        students.add(new Student("Michael Tyoply", "RedGeekPanda", "110313151428733681846"));
        students.add(new Student("Bogdan Rybak", "BogdanRybak1996", "103145064185261665176"));
        students.add(new Student("Ivan Leschenko", "ivleshch", "111088051831122657934"));
        students.add(new Student("Pavlo Sakurov", "sakurov", "108482088578879737406"));
        students.add(new Student("Ruslan Volovyk", "RuslanVolovyk", "109719711261293841416"));
        students.add(new Student("Daria Kyrychenko", "dashakdsr", "103130382244571139113"));
        students.add(new Student("Andriy Ryabko", "RyabkoAndrew", "110288437168771810002"));
        students.add(new Student("Evgeniy Sytnyk", "YevheniiSytnyk", "101427598085441575303"));
        students.add(new Student("Edgar Khimich", "lyfm", "102197104589432395674"));
        students.add(new Student("Alyona Bochkaryouva", "HelenCool", "107382407687723634701"));
        students.add(new Student("Ruslan Mygal", "rmigal", "106331812587299981536"));
        students.add(new Student("Iryna Smalko", "IraSmalko", "113994208318508685327"));
        students.add(new Student("Valeriy Gubskiy", "gvv-ua", "107910188078571144657"));
        students.add(new Student("Evgen Zhdanov", "zhdanov-ek", "113264746064942658029"));
        students.add(new Student("Ivan Sergienko", "dogfight81", "111389859649705526831"));
        students.add(new Student("Igor Paharenko", "IhorPakharenko", "108231952557339738781"));
        students.add(new Student("Oleksandr Storchak", "new15", "106553086375805780685"));
        students.add(new Student("Mykola Pikhmanec", "NikPikhmanets", "110087894894730430086"));
        students.add(new Student("Volodymyr Lymar", "VovanNec", "109227554979939957830"));

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new StudentRecyclerAdapter(students, rootView);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new SwipeHelper((StudentRecyclerAdapter) adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

        return rootView;
    }
}
