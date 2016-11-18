package app.com.example.android.hometask2.model;

/**
 * Created by Влад on 24.10.2016.
 */
public class Student {
    private String nameOfStudent;
    private String git;
    private String account;

    public Student(String nameOfStudent, String git, String account) {
        this.nameOfStudent = nameOfStudent;
        this.git = git;
        this.account = account;
    }

    public String getNameOfStudent() {
        return nameOfStudent;
    }

    public String getGit() {
        return git;
    }

    public String getAccount() {
        return account;
    }
}
