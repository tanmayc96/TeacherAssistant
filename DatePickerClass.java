package com.example.dellpctc.teacherassistant;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerClass extends AppCompatActivity {

    public static String id ="id";
    private DatePicker datePicker;
    private Calendar calendar;
    TextView dateView;
    int Year,month,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker_class);
         dateView = (TextView)findViewById(R.id.date);
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        month =calendar.get(Calendar.MONTH);
        day =  calendar.get(Calendar.DAY_OF_MONTH);
       showDate(day,month,Year);
    }
    public void datePicker(View view){
        showDialog(999);
    }

    public Dialog onCreateDialog(int id){
     if(id==999){
         return new DatePickerDialog(this,myDateListener,Year,month,day);
     }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
             showDate(dayOfMonth,monthOfYear,year);
        }
    };

    private void showDate(int day, int month, int Year) {
       dateView.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(Year));
    }






}
