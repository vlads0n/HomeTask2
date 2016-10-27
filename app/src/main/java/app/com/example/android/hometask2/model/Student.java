package app.com.example.android.hometask2.model;

/**
 * Created by Влад on 24.10.2016.
 */
public class Student {
    private java.lang.String nameOfStudent;
    private java.lang.String git;
    private java.lang.String account;

    public Student(java.lang.String nameOfStudent, java.lang.String git, java.lang.String account) {
        this.nameOfStudent = nameOfStudent;
        this.git = git;
        this.account = account;
    }

    public java.lang.String getNameOfStudent() {
        return nameOfStudent;
    }

    public java.lang.String getGit() {
        return git;
    }

    public java.lang.String getAccount() {
        return account;
    }
}
