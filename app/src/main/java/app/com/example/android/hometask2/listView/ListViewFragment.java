package app.com.example.android.hometask2.listView;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.broadcastReceiver.HeadsetReceiver;
import app.com.example.android.hometask2.broadcastReceiver.PowerReceiver;
import app.com.example.android.hometask2.gplusProfile.AccountGPlusActivity;
import app.com.example.android.hometask2.model.Student;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {
    private final RealmChangeListener<RealmResults<Student>> realmChangeListener = new RealmChangeListener<RealmResults<Student>>() {
        @Override
        public void onChange(RealmResults<Student> element) {
            updateUI(element);
        }
    };
    private HeadsetReceiver headsetReceiver;
    private PowerReceiver powerReceiver;

    private Realm realm;
    private ListView listView;

    public ListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);

        listView = (ListView) rootView.findViewById(R.id.students_list);

        saveStudents(getStudents());
        realm = Realm.getDefaultInstance();
        final RealmResults<Student> students = realm.where(Student.class).findAllAsync();
        students.addChangeListener(realmChangeListener);

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @NonNull
    private List<Student> getStudents() {
        final List<Student> students = new ArrayList<>();

        Student student = new Student();
        student.setNameOfStudent("Vladyslav Vynnyk");
        student.setGit("vlads0n");
        student.setAccount("117765348335292685488");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Michael Tyoply");
        student.setGit("RedGeekPanda");
        student.setAccount("110313151428733681846");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Bogdan Rybak");
        student.setGit("BogdanRybak1996");
        student.setAccount("103145064185261665176");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Ivan Leschenko");
        student.setGit("ivleshch");
        student.setAccount("111088051831122657934");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Pavlo Sakurov");
        student.setGit("sakurov");
        student.setAccount("108482088578879737406");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Ruslan Volovyk");
        student.setGit("RuslanVolovyk");
        student.setAccount("109719711261293841416");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Daria Kyrychenko");
        student.setGit("dashakdsr");
        student.setAccount("103130382244571139113");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Andriy Ryabko");
        student.setGit("RyabkoAndrew");
        student.setAccount("110288437168771810002");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Edgar Khimich");
        student.setGit("lyfm");
        student.setAccount("102197104589432395674");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Ruslan Mygal");
        student.setGit("HelenCool");
        student.setAccount("107382407687723634701");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Iryna Smalko");
        student.setGit("IraSmalko");
        student.setAccount("113994208318508685327");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Valeriy Gubskiy");
        student.setGit("gvv-ua");
        student.setAccount("107910188078571144657");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Evgen Zhdanov");
        student.setGit("zhdanov-ek");
        student.setAccount("113264746064942658029");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Ivan Sergienko");
        student.setGit("dogfight81");
        student.setAccount("111389859649705526831");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Igor Paharenko");
        student.setGit("IhorPakharenko");
        student.setAccount("108231952557339738781");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Oleksandr Storchak");
        student.setGit("new15");
        student.setAccount("106553086375805780685");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Mykola Pikhmanec");
        student.setGit("NikPikhmanets");
        student.setAccount("110087894894730430086");
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Volodymyr Lymar");
        student.setGit("VovanNec");
        student.setAccount("109227554979939957830");
        students.add(student);

        return students;
    }

    private void saveStudents(final List<Student> students) {
        realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(students);
            }
        });
    }

    private void updateUI(RealmResults<Student> students) {
        if (listView.getAdapter() == null)
            listView.setAdapter(new StudentListAdapter(getActivity(), students));
        else {
            StudentListAdapter studentListAdapter = (StudentListAdapter) listView.getAdapter();
            studentListAdapter.notifyDataSetChanged();
        }
    }
}
