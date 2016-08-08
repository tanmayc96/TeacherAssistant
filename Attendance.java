package com.example.dellpctc.teacherassistant;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class Attendance extends AppCompatActivity {
    public static String Choice ="Choice";
    ListAdapter namesAdapter;
    SQLiteOpenHelper sql;
    SQLiteDatabase db;
    int choice ;
    public static String year = "year";
    public static String subject = "subject";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        ListView attendanceList = (ListView) findViewById(R.id.attendanceList);
        dataBaseHandler dbh = new dataBaseHandler(this);
        int s =(int) getIntent().getExtras().get(subject);
        int y = (int) getIntent().getExtras().get(year);
        ArrayList<StudentM> list =  dbh.getAllRecords(y,s);
          namesAdapter = new ListAdapter(this, list);
        attendanceList.setAdapter(namesAdapter);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void saveAll(View view) {
        dataBaseHandler dbh =  new dataBaseHandler(this);
        int y = (int) getIntent().getExtras().get(year);
        int s = (int) getIntent().getExtras().get(subject);


        String date = tellMeDate();
         choice =(int)getIntent().getExtras().get(Choice);
        Spinner spinner = (Spinner)findViewById(R.id.inc);
        String inc = spinner.getSelectedItem().toString();
        int count=0;

        for (StudentM studentM :namesAdapter.getPresent()){
            if(studentM.present){
               dbh.increment(y,s,studentM.getName(),inc,choice,date);
               ++count;
            }
        }
        Toast.makeText(this,"Number of students recorded present :"+count,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Attendance.this,Main2Activity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

public String tellMeDate(){
    sql = new dataBaseHandler(Attendance.this);
    db= sql.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT * FROM DATENAME",null);
    String date = cursor.getString(1);
    db.close();
    return date;


}






}