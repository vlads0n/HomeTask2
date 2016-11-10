package app.com.example.android.hometask2.listView;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import app.com.example.android.hometask2.broadcastReceiver.HeadsetReceiver;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.broadcastReceiver.PowerReceiver;
import app.com.example.android.hometask2.gplusProfile.AccountGPlusActivity;
import app.com.example.android.hometask2.model.Student;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {
    HeadsetReceiver headsetReceiver;
    PowerReceiver powerReceiver;

    public ListViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);

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

        final StudentListAdapter studentListAdapter = new StudentListAdapter(getActivity(), students);

        final ListView listView = (ListView) rootView.findViewById(R.id.students_list);
        listView.setAdapter(studentListAdapter);

//        listView.setOnTouchListener(new View.OnTouchListener() {
//            float x = Float.NaN;
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_UP:
//                        x = motionEvent.getX();
//                        break;
//                    case MotionEvent.ACTION_DOWN:
//                        if (motionEvent.getX() - x < - 250 || motionEvent.getX() - x > 250) {
//                            final Student deleteStudent = students.get(view.getVerticalScrollbarPosition());
//                            students.remove(view.getVerticalScrollbarPosition());
//                            studentListAdapter.notifyDataSetChanged();
//                            Snackbar snackbar = Snackbar
//                                    .make(rootView, "Student is deleted", Snackbar.LENGTH_LONG)
//                                    .setAction("UNDO", new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            students.add(view.getVerticalScrollbarPosition(), deleteStudent);
//                                            studentListAdapter.notifyDataSetChanged();
//                                            Snackbar snackbar1 = Snackbar.make(rootView, "Student is restored!", Snackbar.LENGTH_SHORT);
//                                            snackbar1.show();
//                                        }
//                                    });
//                            snackbar.show();
//                            return true;
//                        }
//                }
//                return false;
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = students.get(position);
                Intent intent = new Intent(getActivity(), AccountGPlusActivity.class).putExtra(Intent.EXTRA_TEXT, student.getAccount());
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        headsetReceiver = new HeadsetReceiver();
        powerReceiver = new PowerReceiver();
        getActivity().registerReceiver(headsetReceiver, new IntentFilter("android.intent.action.HEADSET_PLUG"));
        getActivity().registerReceiver(powerReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(headsetReceiver);
        getActivity().unregisterReceiver(powerReceiver);
    }
}
