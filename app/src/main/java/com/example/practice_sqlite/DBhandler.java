package com.example.practice_sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DBhandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "EmployeeDetails2";

    private static final String TABLE_EMPLOYEE2 = "Employee2";

    private static final String ID_COL = "id";

    private static final String ROLL_NO = "Eid";

    private static final String KEY_NAME = "Ename";

    private static final String KEY_Salary = "Salary";

    private static final String KEY_CREATED_AT = "Created_At";

    EmployeeAdapter adapter;

    ArrayList<EmployeeEntity> employeelist;


    public DBhandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String CREATE_TABLE = "CREATE TABLE " + TABLE_EMPLOYEE2 + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROLL_NO + " INTEGER,"
                + KEY_NAME + " TEXT,"
                + KEY_Salary + " INTEGER,"
                + KEY_CREATED_AT + " DATE)";
        sqLiteDatabase.execSQL(CREATE_TABLE);

        Log.e("==>ds", "onCreate: " + CREATE_TABLE);

        adapter = new EmployeeAdapter();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE2);
        onCreate(sqLiteDatabase);
    }

    public void AddEmployee(EmployeeEntity newemployee) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ROLL_NO, newemployee.getEid());
        values.put(KEY_NAME, newemployee.getEname());
        values.put(KEY_Salary, newemployee.getSalary());
        values.put(KEY_CREATED_AT, new Date().toString());
        db.insert(TABLE_EMPLOYEE2, null, values);
        db.close();
    }

    public void updateEmployee(EmployeeEntity newemployee) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(ROLL_NO, newemployee.getEid());
        data.put(KEY_NAME, newemployee.getEname());
        data.put(KEY_Salary, newemployee.getSalary());
        data.put(KEY_CREATED_AT, new Date().toString());
        db.update(TABLE_EMPLOYEE2, data, "_id=" + ID_COL, null);
        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<EmployeeEntity> getAllEmployee() throws ParseException {

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<EmployeeEntity> emps = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_EMPLOYEE2;
        Cursor c = db.rawQuery(sql, null);

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String dt = c.getString(c.getColumnIndex(KEY_CREATED_AT));

                    SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                            Locale.ENGLISH);
                    Date parsedDate = sdf.parse(dt);
                    SimpleDateFormat print = new SimpleDateFormat("MMM d, yyyy HH:mm:ss");
                    System.out.println(print.format(parsedDate));

                    emps.add(new EmployeeEntity(Integer.parseInt(c.getString(c.getColumnIndex(ROLL_NO))), Integer.parseInt(c.getString(c.getColumnIndex(KEY_Salary))), c.getString(c.getColumnIndex(KEY_NAME)), parsedDate));
                }
                while (c.moveToNext());
            }
            return emps;
        }

        return getAllEmployee();

    }

    public void deleteEntry(long row) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_EMPLOYEE2, ID_COL + "=" + row, null);
    }

//    public void delete(int  i) {
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        sqLiteDatabase.delete(TABLE_EMPLOYEE2, ID_COL, null);
//    }
}
