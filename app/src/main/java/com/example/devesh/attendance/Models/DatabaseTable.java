package com.example.devesh.attendance.Models;

/**
 * Created by devesh on 30/8/16.
 */
public class DatabaseTable extends Table {

    public static final String TABLE_NAME = "Attendance_Tracker";

    public static final String TABLE_CREATE_CMD ="CREATE TABLE IF NOT EXISTS "+TABLE_NAME
            +LBR
            +Columns.SUBJECT_CODE+ TYPE_TEXT + COMMA
            +Columns.SUBJECT + TYPE_TEXT + COMMA
            +Columns.TEACHER + TYPE_TEXT + COMMA
            +Columns.PAST + TYPE_TEXT + COMMA
            +Columns.ATTENDED + TYPE_INT + COMMA
            +Columns.TOTAL + TYPE_INT
            +RBR+";";

    public interface Columns{
        String SUBJECT_CODE = "code";
        String SUBJECT = "sub";
        String TEACHER = "teacher";
        String PAST = "past";
        String ATTENDED = "attended";
        String TOTAL = "total";
    }
}
