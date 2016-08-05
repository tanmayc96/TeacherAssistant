package com.example.dellpctc.teacherassistant;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableLayoutFile extends AppCompatActivity {
    Spinner spinner;
    TableLayout tbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
         spinner = (Spinner)findViewById(R.id.year3);
        tbl = (TableLayout) findViewById(R.id.tableL);


    }
       public void ViewTable(View view){

          tbl.removeAllViewsInLayout();
           String year = spinner.getSelectedItem().toString();
           BuildTable(year);}

    private void BuildTable(String year) {
        dataBaseHandler dbh = new  dataBaseHandler(this);
        Cursor c = dbh.allEntry(year);
        int row = c.getCount();
        int col = c.getColumnCount();
        TableRow tbr = new TableRow(this);

        for(int i =0;i<col;++i){
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
              tv.setBackgroundResource(R.drawable.cell_shape);
            tv.setGravity(Gravity.CENTER);
               tv.setTextSize(18);

               tv.setPadding(0,5,0,5);
               tv.setText(" "+c.getColumnName(i)+" ");

               tbr.addView(tv);
           }
        tbl.addView(tbr);
        c.moveToFirst();
        for(int r =0;r<row;++r){
            TableRow tableRow = new TableRow(this);

            tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
           for(int j=0; j<col;++j){
               TextView tv = new TextView(this);
               tv.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
               tv.setBackgroundResource(R.drawable.cell_shape);
              tv.setGravity(Gravity.CENTER);
               tv.setTextSize(18);
               tv.setPadding(0,5,0,5);
               tv.setText(" "+c.getString(j)+" ");
               tableRow.addView(tv);
           }
            c.moveToNext();
            tbl.addView(tableRow);
        }

    }
}
