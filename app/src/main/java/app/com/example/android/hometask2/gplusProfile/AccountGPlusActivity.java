package app.com.example.android.hometask2.gplusProfile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import app.com.example.android.hometask2.R;

public class AccountGPlusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        toolbar.setTitle("G+ profile");

        AccountGPlusFragment accountGPlusFragment = new AccountGPlusFragment();
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, accountGPlusFragment).commit();
    }
}
