package app.com.example.android.hometask2.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import app.com.example.android.hometask2.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        MainFragment mainFragment = new MainFragment();
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
    }
}
