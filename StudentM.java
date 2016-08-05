package com.example.dellpctc.teacherassistant;

/**
 * Created by Dell pc TC on 6/20/2016.
 */
public class StudentM {
public String Name;
public Boolean check = false;
public Boolean present = false ;

 public StudentM(String n){
    this.Name =n;
 }

    public StudentM() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public Boolean isChecked() {
        return check;
    }

    @Override
    public String toString() {
        return this.Name;
    }
}
