package com.example.dellpctc.teacherassistant;

/**
 * Created by Dell pc TC on 7/8/2016.
 */
public class DisplayIcon {
    String DName;
    Integer DId;
    public static DisplayIcon[] displayIcons = {new DisplayIcon("Add Student",R.drawable.add),new DisplayIcon("Delete record",R.drawable.delete),
                              new DisplayIcon("Take attendance",R.drawable.attendance), new DisplayIcon("View Table",R.drawable.view),
            new DisplayIcon("Upload",R.drawable.upload),new DisplayIcon("Edit",R.drawable.edit),new DisplayIcon("Exit",R.drawable.exit)
    };

    public DisplayIcon(String name, int id) {
        this.DName=name;
        this.DId=id;
    }

    @Override
    public String toString() {
        return this.DName;
    }

    public Integer getDId() {
        return DId;
    }

    public String getDName() {
        return DName;
    }

    public void setDName(String DName) {
        this.DName = DName;
    }

    public void setDId(Integer DId) {
        this.DId = DId;
    }
}
