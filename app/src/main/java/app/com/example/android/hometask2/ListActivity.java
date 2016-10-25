package app.com.example.android.hometask2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListFragment listFragment = new ListFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.container, listFragment).commit();
    }
}
