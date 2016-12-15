package app.com.example.android.hometask2.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Влад on 24.10.2016.
 */
public class Student extends RealmObject {

    @PrimaryKey
    private String nameOfStudent;
    private String searchName;
    private String git;
    private String account;

    public String getNameOfStudent() {
        return nameOfStudent;
    }

    public String getSearchName() {
        return searchName;
    }

    public String getGit() {
        return git;
    }

    public String getAccount() {
        return account;
    }

    public void setNameOfStudent(String nameOfStudent) {
        this.nameOfStudent = nameOfStudent;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName.toLowerCase();
    }

    public void setGit(String git) {
        this.git = git;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
