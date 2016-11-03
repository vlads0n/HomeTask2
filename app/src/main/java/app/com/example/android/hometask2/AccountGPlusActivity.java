package app.com.example.android.hometask2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AccountGPlusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_gplus);

        AccountGPlusFragment accountGPlusFragment = new AccountGPlusFragment();
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, accountGPlusFragment).commit();
    }
}
