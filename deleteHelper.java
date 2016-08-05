package com.example.dellpctc.teacherassistant;

/**
 * Created by Dell pc TC on 7/15/2016.
 */
public class deleteHelper {

    String name;
    Integer id;
    public static deleteHelper[] deleteHelpers ={new deleteHelper("Reset data",R.drawable.reset),new deleteHelper("Delete data",R.drawable.deletedata),
    new deleteHelper("Drop Table",R.drawable.drop)};

    public deleteHelper(String name, int id) {
        this.name=name;
        this.id=id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
