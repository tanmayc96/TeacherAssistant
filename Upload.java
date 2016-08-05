package com.example.dellpctc.teacherassistant;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Upload extends AppCompatActivity {
     final  String myTag ="DOCSUPLOAD";
    List<String> ret;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pload);

    }
    public void uploadData(View view){
        new SendData().execute();
    }

    private void upload() {
        String fullURL ="https://docs.google.com/forms/d/e/1FAIpQLScJqqc-e6TN9CX3oHSaP-yHbhU04H70U_bx4KsRTnvNJoVeBQ/formResponse";
        String response = null;
        dataBaseHandler dbh = new dataBaseHandler(this);
        HttpRequest up = new HttpRequest();
        Spinner year = (Spinner)findViewById(R.id.year4);

        String y = year.getSelectedItem().toString();
        Cursor c = dbh.allEntry(y);
        int row = c.getCount();
        c.moveToFirst();
        Boolean UploadList = false;
        for(int i= 0;i<row;++i)
        { String id = c.getString(0);
        String name = c.getString(1);
        String C = c.getString(2);
        String CSA = c.getString(3);
        String Java = c.getString(4);
        String DS = c.getString(5);
        String data = "entry.982991520=" + URLEncoder.encode(id)+ "&" +  "entry.1935071110=" + URLEncoder.encode(name) +
                "&" + "entry.1111832683=" + URLEncoder.encode(C) + "&" + "entry.1831822908=" + URLEncoder.encode(CSA) +
                "&" + "entry.257134684=" + URLEncoder.encode(Java) + "&"+"entry.936130452=" + URLEncoder.encode(DS) ;
            String s =up.sendPost(fullURL,data);

            c.moveToNext();

        }

    }


    private class SendData extends AsyncTask<Void,Void,Boolean> {
     List<String> s=new ArrayList<>();
        Boolean result=false;
        TextView tv;
        @Override
        protected Boolean doInBackground(Void... params) {
            try{
                upload();
                result=true;
            }catch (Exception e){
                result =false;
            }
             return  result;
        }
        @Override
        protected void onPostExecute(Boolean list) {
            tv = (TextView)findViewById(R.id.uploadLog);
            if(list)
            {   tv.setTextSize(20);
                tv.setText("Upload was successfull");
            }
              else
            {
                tv.setTextSize(20);
                tv.setText("Upload was unsuccesfull");
            }
        }
    }
}
