package com.example.devesh.attendance;

/**
 * Created by devesh on 30/8/16.
 */
public class Info {

    String Subject_Code;
    String Subject;
    String Teacher;
    String Past;
    int Attended;
    int Total;
    int id;

    public Info(String subject_Code, String subject, String teacher, String past, int attended, int total, int id) {
        Subject_Code = subject_Code;
        Subject = subject;
        Teacher = teacher;
        Past = past;
        Attended = attended;
        Total = total;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject_Code() {
        return Subject_Code;
    }

    public void setSubject_Code(String subject_Code) {
        Subject_Code = subject_Code;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String teacher) {
        Teacher = teacher;
    }

    public String getPast() {
        return Past;
    }

    public void setPast(String past) {
        Past = past;
    }

    public int getAttended() {
        return Attended;
    }

    public void setAttended(int attended) {
        Attended = attended;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }
}
