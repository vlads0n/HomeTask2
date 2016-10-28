package app.com.example.android.hometask2.listView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import app.com.example.android.hometask2.R;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ListViewFragment listViewFragment = new ListViewFragment();
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, listViewFragment).commit();
    }
}
