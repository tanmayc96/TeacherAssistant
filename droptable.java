package com.example.dellpctc.teacherassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class droptable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_droptable);
    }
 public void dropTable(View view) throws InterruptedException {
     Spinner spinner = (Spinner)findViewById(R.id.year6);
     String year = spinner.getSelectedItem().toString();
     dataBaseHandler dbh = new dataBaseHandler(this);
     Boolean result = dbh.dropTable(year);
     String r=null;
     if(result)
         r="Table"+year+" has been suceessfully dropped";
     else
       r="Unsuccesfull attempt";
     Toast.makeText(droptable.this,r,Toast.LENGTH_SHORT).show();

     Intent intent = new Intent(droptable.this,Main2Activity.class);
     startActivity(intent);
 }
}
