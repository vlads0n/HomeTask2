package app.com.example.android.hometask2.recyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import app.com.example.android.hometask2.R;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        toolbar.setTitle("RecyclerView");

        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, recyclerViewFragment).commit();
    }
}
