package com.example.dellpctc.teacherassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  String query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        }

    public void onLogin(View view){
       EditText loginName =(EditText)findViewById(R.id.LoginName);
       EditText password =(EditText)findViewById(R.id.password);
       logInCheck loginCheck = new logInCheck();
       boolean check = loginCheck.check(loginName.getText().toString(),password.getText().toString());
       if(check==true)
       {
           Intent intent = new Intent(this,Main2Activity.class);
           startActivity(intent);

       }
       else
       {Toast.makeText(this,"User name and password do not match",Toast.LENGTH_SHORT).show();}
          loginName.setText("");
       password.setText("");
   }



}
