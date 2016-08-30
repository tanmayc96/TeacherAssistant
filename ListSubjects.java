package com.example.dellpctc.teacherassistant;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListSubjects extends ListActivity {

    public static final String Year ="Year";
    public static String Choice ="Choice";
    int choice ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       ListView listView = getListView();
        int year  = (int) getIntent().getExtras().get(Year);
        List<String> subjectList = displayList(year);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,subjectList);
        listView.setAdapter(listAdapter);

    }



  public void onListItemClick(ListView listview,View itemview,int position,long id){
      new addCol().execute();
      int year  = (int) getIntent().getExtras().get(Year);
     int subject = (int)id;
       choice = (int) getIntent().getExtras().get(Choice);
      Intent intent = new Intent(this,Attendance.class);
      intent.putExtra(Attendance.year,year);
      intent.putExtra(Attendance.subject,subject);
      intent.putExtra(Attendance.Choice,choice);
      startActivity(intent);
  }

    private List<String> displayList(int year) {
        List<String> subject = new ArrayList<>();

        switch (year) {
            case 0:
                subject.add("C");
                subject.add("CSA");
                subject.add("Java");
                subject.add("Data Structure");
                break;
            case 1:
                subject.add("System Programming");
                subject.add("Algorithm");
                subject.add("Probability");
                subject.add("DBMS");
                break;
            case 2:
                subject.add("Internet Technologies");

        }




       return subject;
    }

    private class addCol extends AsyncTask<Void,Void,Boolean> {
        String dateName;
         int count=0;
        String s;
        @Override
        protected void onPreExecute() {
            dataBaseHandler dbh = new dataBaseHandler(ListSubjects.this);
            Cursor c = dbh.allEntry2("DATENAME");
            count= c.getCount();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
                      int day=0;
            int month=0;
            SQLiteOpenHelper sql = new dataBaseHandler(ListSubjects.this);
            SQLiteDatabase db = sql.getReadableDatabase();
            Cursor cursor = db.query("DateKeeper",new String[]{"_id","DAY","MONTH"}," _id = ? ",new String[]{Integer.toString(1)},null,null,null);
            if(cursor.moveToFirst())
               {
               day = cursor.getInt(1);
                month = cursor.getInt(2);
               }
            dateName= String.valueOf(new StringBuilder().append("O").append(day).append("_").append(month));
            SQLiteDatabase db2 = sql.getWritableDatabase();
            Cursor c = db2.query("DATENAME",new String[]{"_id","NAME"}," _id = ? ",new String[]{Integer.toString(1)},null,null,null);
            if(c.moveToFirst()){
                 s = c.getString(1); //checking if same name exist or not
            }

            ContentValues cv= new ContentValues();
            cv.put("NAME",dateName);
            if(count==0){
                db2.insert("DATENAME",null,cv);
            }
            else if(!s.equals(dateName)){
                db2.insert("DATENAME",null,cv);
            }

            return null;
        }
    }
}
