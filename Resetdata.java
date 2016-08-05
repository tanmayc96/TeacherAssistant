package com.example.dellpctc.teacherassistant;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Resetdata extends AppCompatActivity {
TableLayout tableLayout;
Cursor c;
SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetdata);
        tableLayout =(TableLayout)findViewById(R.id.tableL3);
    }
    public void resetData(View view){
        tableLayout.removeAllViewsInLayout();
       Spinner  spinner = (Spinner)findViewById(R.id.year5);
        String y = spinner.getSelectedItem().toString();

        new Clear().execute(y);


    }

    public void display(){
        Spinner  spinner = (Spinner)findViewById(R.id.year5);
        String y = spinner.getSelectedItem().toString();
        dataBaseHandler dbh = new dataBaseHandler(this);
        c = dbh.allEntry(y);
        int col= c.getColumnCount();
        int row = c.getCount();
        TableRow tbr = new TableRow(this);
        for (int i = 0; i < col; ++i) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(0, 5, 0, 5);
            tv.setBackgroundResource(R.drawable.cell_shape);
            tv.setTextSize(20);
            tv.setText(" " + c.getColumnName(i) + " ");
            tbr.addView(tv);
        }
        tableLayout.addView(tbr);
        c.moveToFirst();
        for (int i = 0; i < row; ++i) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < col; ++j) {
                TextView tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                tv.setBackgroundResource(R.drawable.cell_shape);
                tv.setPadding(0, 5, 0, 5);
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(20);
                tv.setText(" " + c.getString(j) + " ");
                tableRow.addView(tv);
            }
            tableLayout.addView(tableRow);
            c.moveToNext();
        }

    }

    public Boolean setZero (String year) {
        Boolean result = true;
        dataBaseHandler dbh = new dataBaseHandler(Resetdata.this);
           ContentValues contentValues = new ContentValues();
            SQLiteOpenHelper d = new dataBaseHandler(Resetdata.this);
             db= d.getWritableDatabase();
        String tbName = null;
            switch (year){
                case "Ist":tbName="FIRSTYR";
                           break;
                case "IInd":tbName="SECONDYR";
                              break;

            }
               c=dbh.allEntry(year);
              int col =c.getColumnCount();
            int row = c.getCount();
            int count =0;
            String colName= null;
            c.moveToFirst();
            try {
                for (int i = 0; i < row; ++i) {
                    int roll = c.getInt(0);
                    for (int j = 2; j < col; ++j) {
                        colName = c.getColumnName(j);
                        contentValues.put(colName, count);
                        db.update(tbName, contentValues, " _id = ?", new String[]{Integer.toString(roll)});
                    }
                    c.moveToNext();
                }
            }catch (Exception e){
                result = false;
            }
        return result;
        }
@Override
public void onDestroy(){
    super.onDestroy();
    c.close();
    db.close();
}

    private class Clear extends AsyncTask<String,Void,Boolean> {
        Boolean result;
        @Override
        protected Boolean doInBackground(String... params) {
            String y = params[0];
            result = setZero(y);
            return result;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean)
                display();
            else
                Toast.makeText(Resetdata.this,"sorry",Toast.LENGTH_SHORT).show();
        }
    }
}
