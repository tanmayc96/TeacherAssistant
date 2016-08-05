package com.example.dellpctc.teacherassistant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class DatePickerClass2 extends AppCompatActivity {
    private Calendar calendar;
    TextView dateView;
    int Year,month,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker_class2);

    }

    public void datePicker2(View view){
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioG);
        int id = radioGroup.getCheckedRadioButtonId();

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        switch (id){
            case R.id.R1 : alert.setTitle("Choose subject");
                        final ArrayList<Integer> list = new ArrayList<Integer>();
                           alert.setMultiChoiceItems(R.array.Ist, null, new DialogInterface.OnMultiChoiceClickListener() {

                               @Override
                               public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                   assert false;
                                   list.add( which);
                               }
                           });
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      Intent intent = new Intent(DatePickerClass2.this,SetDate.class);
                        intent.putExtra(SetDate.tbName,0);
                        intent.putIntegerArrayListExtra(SetDate.list,list);
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getApplicationContext();
                    }
                });

                break;
            case R.id.R2 : alert.setTitle("Choose subject");
                final ArrayList<Integer>  list2 = new ArrayList<Integer>();
                alert.setMultiChoiceItems(R.array.IInd, null, new DialogInterface.OnMultiChoiceClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        list2.add(which);
                    }
                });
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(DatePickerClass2.this,SetDate.class);
                        intent.putExtra(SetDate.tbName,1);
                        intent.putIntegerArrayListExtra(SetDate.list,list2);
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getApplicationContext();
                    }
                });

        }
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }


}
