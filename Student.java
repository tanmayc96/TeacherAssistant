package com.example.dellpctc.teacherassistant;

/**
 * Created by Dell pc TC on 6/18/2016.
 */
public class Student {
    private int STUDENT_ID;
    private String NAME;
    private Integer COL1;
    private Integer COL2;
    private Integer COL3;
    private Integer COL4;


    public Student(int id, String name , Integer a, Integer b, Integer c, Integer d){
        this.STUDENT_ID=id;
        this.NAME=name;
        this.COL1=a;
        this.COL2=b;
        this.COL3=c;
        this.COL4=d;
    }

    public Student() {

    }


    public int getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public Integer getCOL1() {
        return COL1;
    }

    public String getNAME() {
        return NAME;
    }

    public Integer getCOL2() {
        return COL2;
    }

    public Integer getCOL3() {
        return COL3;
    }

    public void setCOL1(Integer COL1) {
        this.COL1 = COL1;
    }

    public void setCOL2(Integer COL2) {
        this.COL2 = COL2;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setSTUDENT_ID(int STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
    }

    public Integer getCOL4() {
        return COL4;
    }

    public void setCOL3(Integer COL3) {
        this.COL3 = COL3;
    }

    public void setCOL4(Integer COL4) {
        this.COL4 = COL4;
    }

    @Override
    public String toString() {
        return this.NAME;
    }
}


