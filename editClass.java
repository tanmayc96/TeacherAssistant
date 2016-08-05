package com.example.dellpctc.teacherassistant;

/**
 * Created by Dell pc TC on 7/26/2016.
 */
public class editClass {
    String Name;
    int imgId;
    public static editClass[] editClasses ={  new editClass("Set start date",R.drawable.date),new editClass("Change in attendance",R.drawable.edit2)};

    public editClass(String s, int date) {
        this.Name =s;
        this.imgId = date;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
