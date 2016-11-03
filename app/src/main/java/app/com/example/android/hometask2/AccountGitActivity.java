package app.com.example.android.hometask2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AccountGitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_git);

        AccountGitFragment accountGitFragment = new AccountGitFragment();
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, accountGitFragment).commit();
    }
}
