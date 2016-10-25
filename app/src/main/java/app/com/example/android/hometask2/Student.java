package app.com.example.android.hometask2;

/**
 * Created by Влад on 24.10.2016.
 */
public class Student {
    private String nameOfStudent;
    private String git;

    public Student(String nameOfStudent, String git) {
        this.nameOfStudent = nameOfStudent;
        this.git = git;
    }

    public String getNameOfStudent() {
        return nameOfStudent;
    }

    public String getGit() {
        return git;
    }
}
