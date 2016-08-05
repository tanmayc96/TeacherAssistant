package com.example.dellpctc.teacherassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DeleteData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data);
    }

    public void onDelete(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.year2);
        EditText editText = (EditText) findViewById(R.id.roll2);
        dataBaseHandler dbh = new dataBaseHandler(this);
        String tbname = spinner.getSelectedItem().toString();
        Integer roll = Integer.valueOf(editText.getText().toString());
       Boolean result = dbh.onDelete(tbname,roll);
        if(result==true)
            Toast.makeText(this,"Data has been successfully deleted",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"error encountered onDelete",Toast.LENGTH_SHORT).show();

    }
    public void seeList(View view){
        Spinner spinner = (Spinner)findViewById(R.id.year2);
        Intent intent = new Intent(this,TableLayout2.class);
        intent.putExtra(TableLayout2.year,spinner.getSelectedItem().toString());
        startActivity(intent);
    }

}