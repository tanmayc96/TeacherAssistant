package com.example.dellpctc.teacherassistant;

/**
 * Created by Dell pc TC on 6/26/2016.
 */
public class Year {
    private String year ;
    public static Year[] years ={ new Year("Ist"),new Year ("IInd"), new Year ("IIIrd")};

    public Year(String year) {
        this.year=year;
    }

    @Override
    public String toString() {
        return this.year;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
