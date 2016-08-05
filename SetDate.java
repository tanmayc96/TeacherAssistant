package com.example.dellpctc.teacherassistant;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SetDate extends AppCompatActivity {

    public static String tbName = "tbName";
    public static String list = "list";
    private Calendar calendar;
    int m,d;
    int yr;
    TextView date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_date);
        date = (TextView)findViewById(R.id.dateV);
        List<String> subjectList = new ArrayList<String>();

        int y = (int) getIntent().getIntExtra(tbName,0);
        ArrayList<Integer> List = getIntent().getIntegerArrayListExtra(list);
        subjectList = getSubjectList(List,y);//getting the subject list
        ListView listV =(ListView)findViewById(R.id.chosenSubjects);
        ArrayAdapter<String> arrayAd = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,subjectList);
        listV.setAdapter(arrayAd);
        calendar = Calendar.getInstance();
        yr = calendar.get(Calendar.YEAR);
        m = calendar.get(Calendar.MONTH);
        d= calendar.get(Calendar.DAY_OF_MONTH);
       // showDate(yr,m,d);

    }

    private List<String> getSubjectList(ArrayList<Integer> id, int tbName) {
        List<String> SubjectList = new ArrayList<String>();
        if (tbName == 0) {
            for (Integer i : id) {
                switch (i) {
                    case 0:SubjectList.add("C");
                        break;
                    case 1:  SubjectList.add("CSA");
                        break;
                    case 2:SubjectList.add("JAVA");
                        break;
                    case 3:SubjectList.add("DS");

                }
            }
        }
        else if(tbName==1){
            for (Integer i : id) {
                switch (i) {
                    case 0:SubjectList.add("ALGO");
                        break;
                    case 1:  SubjectList.add("DCN");
                        break;
                    case 2:SubjectList.add("OS");
                        break;
                    case 3:SubjectList.add("SP");

                }
            }

        }
        return SubjectList;
    }



    private void showDate(int year, int month, int day) {
        date.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

    public void chooseDate(View view) {

            showDialog(999);
    }
    public Dialog onCreateDialog(int id){
        if(id==999){
            return new DatePickerDialog(this,myDateListener,yr,m,d);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            showDate(year,monthOfYear,dayOfMonth);
        }
    };
    public void setDate(View view){
        String s = date.getText().toString();  //to store
        SQLiteOpenHelper sqLiteOpenHelper = new dataBaseHandler(SetDate.this);
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        int y = getIntent().getIntExtra(tbName,0);
        List<String> subjectList= new ArrayList<String>() ;
        ArrayList<Integer> List = getIntent().getIntegerArrayListExtra(list);
        subjectList = getSubjectList(List,y);

        ContentValues cv;
        switch(y){
            case 0: for(String subject :subjectList){
                       cv= new ContentValues();
                     cv.put(subject,s);
                      db.update("STARTDATEF",cv," _id = ?",new String[]{Integer.toString(1)});
                      }
                break;
            case 1:for(String subject :subjectList){
                cv= new ContentValues();
                cv.put(subject,s);
                db.update("STARTDATES",cv,null,null);
            }
        }
    }
}
