package com.example.dellpctc.teacherassistant;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableLayout2 extends AppCompatActivity {
    public static String year="year";
    TableLayout tbl2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout2);
        tbl2 = (TableLayout)findViewById(R.id.tableL2);
        String y = getIntent().getStringExtra(year);
        buildTable(y);
    }

    private void buildTable(String year) {
        dataBaseHandler dbh = new dataBaseHandler(this);
        Cursor c = dbh.allEntry(year);
        int columns = c.getColumnCount();
        int row = c.getCount();
        c.moveToFirst();
        TableRow tableRow = new TableRow(this);
        for (int i = 0; i<2;++i){
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tv.setBackgroundResource(R.drawable.cell_shape);
             tv.setTextSize(30);
            tv.setPadding(0,5,0,5);
            tv.setGravity(Gravity.CENTER);
            tv.setText(" "+c.getColumnName(i)+" ");
            tableRow.addView(tv);
        }
       tbl2.addView(tableRow);
        c.moveToFirst();
      for(int i = 0;i<row;++i){
          TableRow tableRow2 = new TableRow(this);
          tableRow2.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
      for(int j =0;j<2;++j){
          TextView tv = new TextView(this);
          tv.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
          tv.setBackgroundResource(R.drawable.cell_shape);
          tv.setTextSize(30);
          tv.setPadding(0,5,0,5);
          tv.setGravity(Gravity.CENTER);
          tv.setText(" "+c.getString(j)+" ");
          tableRow2.addView(tv);
      }
         tbl2.addView(tableRow2);
          c.moveToNext();
      }

c.close();
    }
}
