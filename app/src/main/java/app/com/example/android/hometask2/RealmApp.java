package app.com.example.android.hometask2;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Влад on 15.12.2016.
 */
public class RealmApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("student_list.realm")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
