package com.example.dellpctc.teacherassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
//viewing the change
public class AddData extends AppCompatActivity {
    String Year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

    public void onAdd(View view) {
        Spinner year = (Spinner) findViewById(R.id.year);
        EditText name = (EditText) findViewById(R.id.name);
        EditText roll_id = (EditText) findViewById(R.id.roll);
        dataBaseHandler dbh = new dataBaseHandler(this);
        Year = year.getSelectedItem().toString();
        String Name = name.getText().toString();
        int Roll = Integer.parseInt(roll_id.getText().toString());
        Boolean result = dbh.onAdd(Year, Roll, Name);
        if (result) {
            Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"onAdd database excetion",Toast.LENGTH_SHORT).show();
           name.setText("");
           roll_id.setText("");


    }
    public void seeList(View view){
       Spinner year =(Spinner)findViewById(R.id.year);
        String y = year.getSelectedItem().toString();
         Intent intent = new Intent(this,TableLayout2.class);
        intent.putExtra(TableLayout2.year,y);
        startActivity(intent);
    }


}