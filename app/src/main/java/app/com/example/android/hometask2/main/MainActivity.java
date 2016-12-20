package app.com.example.android.hometask2.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.getContacts.ContactsActivity;
import app.com.example.android.hometask2.getPhoto.GetPhotoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        MainFragment mainFragment = new MainFragment();
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.student_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.get_contacts:
                Intent intentContacts = new Intent(this, ContactsActivity.class);
                startActivity(intentContacts);
                return true;
            case R.id.get_photo:
                Intent intentPhoto = new Intent(this, GetPhotoActivity.class);
                startActivity(intentPhoto);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
