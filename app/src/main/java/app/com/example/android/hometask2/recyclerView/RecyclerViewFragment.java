package app.com.example.android.hometask2.recyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.*;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.broadcastReceiver.HeadsetReceiver;
import app.com.example.android.hometask2.broadcastReceiver.PowerReceiver;
import app.com.example.android.hometask2.model.Student;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {
    private final RealmChangeListener<RealmResults<Student>> realmChangeListener = new RealmChangeListener<RealmResults<Student>>() {
        @Override
        public void onChange(RealmResults<Student> element) {
            updateUI(element);

            ItemTouchHelper.Callback callback = new SwipeHelper((StudentRecyclerAdapter) adapter);
            ItemTouchHelper helper = new ItemTouchHelper(callback);
            helper.attachToRecyclerView(recyclerView);
        }
    };
    private View rootView;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private HeadsetReceiver headsetReceiver;
    private PowerReceiver powerReceiver;
    private Realm realm;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        saveStudents(getStudents());
        realm = Realm.getDefaultInstance();
        final RealmResults<Student> students = realm.where(Student.class).findAllAsync();
        students.addChangeListener(realmChangeListener);

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
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Michael Tyoply");
        student.setGit("RedGeekPanda");
        student.setAccount("110313151428733681846");
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Bogdan Rybak");
        student.setGit("BogdanRybak1996");
        student.setAccount("103145064185261665176");
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Ivan Leschenko");
        student.setGit("ivleshch");
        student.setAccount("111088051831122657934");
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Pavlo Sakurov");
        student.setGit("sakurov");
        student.setAccount("108482088578879737406");
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Ruslan Volovyk");
        student.setGit("RuslanVolovyk");
        student.setAccount("109719711261293841416");
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Daria Kyrychenko");
        student.setGit("dashakdsr");
        student.setAccount("103130382244571139113");
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Andriy Ryabko");
        student.setGit("RyabkoAndrew");
        student.setAccount("110288437168771810002");
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Edgar Khimich");
        student.setGit("lyfm");
        student.setAccount("102197104589432395674");
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Ruslan Mygal");
        student.setGit("HelenCool");
        student.setAccount("107382407687723634701");
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Iryna Smalko");
        student.setGit("IraSmalko");
        student.setAccount("113994208318508685327");
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Valeriy Gubskiy");
        student.setGit("gvv-ua");
        student.setAccount("107910188078571144657");
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Evgen Zhdanov");
        student.setGit("zhdanov-ek");
        student.setAccount("113264746064942658029");
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Ivan Sergienko");
        student.setGit("dogfight81");
        student.setAccount("111389859649705526831");
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Igor Paharenko");
        student.setGit("IhorPakharenko");
        student.setAccount("108231952557339738781");
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Oleksandr Storchak");
        student.setGit("new15");
        student.setAccount("106553086375805780685");
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Mykola Pikhmanec");
        student.setGit("NikPikhmanets");
        student.setAccount("110087894894730430086");
        student.setSearchName(student.getNameOfStudent());
        students.add(student);

        student = new Student();
        student.setNameOfStudent("Volodymyr Lymar");
        student.setGit("VovanNec");
        student.setAccount("109227554979939957830");
        student.setSearchName(student.getNameOfStudent());
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
        if (recyclerView.getAdapter() == null) {
            adapter = new StudentRecyclerAdapter(students, rootView, realm);
            recyclerView.setAdapter(adapter);
        }
        else {
            StudentRecyclerAdapter studentRecyclerAdapter = (StudentRecyclerAdapter) recyclerView.getAdapter();
            studentRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        if (null != searchManager) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        }
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                RealmResults<Student> realmResults = realm.where(Student.class).contains("searchName", newText).findAll();
                adapter = new StudentRecyclerAdapter(realmResults, rootView, realm);
                recyclerView.setAdapter(adapter);
                return true;
            }
        });
    }
}
