package com.example.dellpctc.teacherassistant;

/**
 * Created by Dell pc TC on 6/17/2016.
 */
public class logInCheck {
String name;
String password;
Boolean result= false;
    public boolean check(String name,String password){
      if(name.equals("Tanmay")&&password.equals("tanmay"))
                    result=true;
      else if(name.equals("Sakshi")&&password.equals("sakshi"))
          result=true;
        else result=false;

        return result;}



}
