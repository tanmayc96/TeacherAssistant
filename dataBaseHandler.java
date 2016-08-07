package com.example.dellpctc.teacherassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Dell pc TC on 6/17/2016.
 */
public class dataBaseHandler extends SQLiteOpenHelper {
    private static final String DB_NAME="TEACHER";
    private static final int DB_VERSION= 1;

    //TABLE1 Theory
    private static final String TB_NAME1="C";
    private static final String T1STUDENT_ID = "_id";
    private static final String T1NAME="NAME";


   //TABLE2 theory
    private static final String TB_NAME2="CSA";
    private static final String T2STUDENT_ID = "_id";
    private static final String T2NAME="NAME";
     //Table3 theory
    private static final String TB_NAME3="JAVA";
    private static final String T3STUDENT_ID = "_id";
    private static final String T3NAME="NAME";
   //Table4
   private static final String TB_NAME4="DS";
    private static final String T4STUDENT_ID = "_id";
    private static final String T4NAME="NAME";

    public dataBaseHandler(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createT1 = " CREATE TABLE " + TB_NAME1 + " ( "+T1STUDENT_ID + " INTEGER PRIMARY KEY, " +
                T1NAME + " TEXT);" ;

        String createT2 = " CREATE TABLE "+TB_NAME2 + " ( "+T2STUDENT_ID +" INTEGER PRIMARY KEY, "+
                T2NAME + " TEXT );";
        String createT3 = " CREATE TABLE "+TB_NAME3 + " ( "+T3STUDENT_ID +" INTEGER PRIMARY KEY, "+
                T3NAME + " TEXT );";
        String createT4 = " CREATE TABLE "+TB_NAME4 + " ( "+T4STUDENT_ID +" INTEGER PRIMARY KEY, "+
                T4NAME + " TEXT );";

        db.execSQL(createT1);
        db.execSQL(createT2);
        db.execSQL(createT3);
        db.execSQL(createT4);
    }






    public Boolean onAdd(String year, Integer id, String name){
        Boolean result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try{if(year.equals("Ist")) {
            contentValues.put(T1STUDENT_ID,id);   //c
            contentValues.put(T1NAME,name);
            db.insert(TB_NAME1,null,contentValues);

            contentValues.put(T2STUDENT_ID,id);  //csa
            contentValues.put(T2NAME,name);
            db.insert(TB_NAME2,null,contentValues);

            contentValues.put(T3STUDENT_ID,id);   //java
            contentValues.put(T3NAME,name);
            db.insert(TB_NAME3,null,contentValues);

            contentValues.put(T4STUDENT_ID,id); //ds
            contentValues.put(T4NAME,name);
            db.insert(TB_NAME4,null,contentValues);
            }
        else{
            contentValues.put(T2STUDENT_ID,id);
            contentValues.put(T2NAME,name);
            db.insert(TB_NAME2,null,contentValues);
            }
            result = true;
        } catch (SQLiteException e){
           result=false;
        }
        db.close();
     return result;
    }



    public Boolean onDelete (String tbName, Integer id){

        Boolean result;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            if(tbName.equals("Ist")){
                db.delete(TB_NAME1,"_id = ?" ,new String[]{Integer.toString(id)});
                db.delete(TB_NAME2,"_id = ?" ,new String[]{Integer.toString(id)});
                db.delete(TB_NAME3,"_id = ?" ,new String[]{Integer.toString(id)});
                db.delete(TB_NAME4,"_id = ?" ,new String[]{Integer.toString(id)});
            }
            else
            db.delete(TB_NAME2,"_id = ?",new String[]{Integer.toString(id)});
             result =true;

        }catch (SQLiteException e){
            result = false;
        }
        db.close();
        return result;
    }
public Cursor allEntry(String year){
    Cursor cursor = null;
    SQLiteDatabase db = this.getReadableDatabase();
    if(year.equals("Ist")){
        String query = "SELECT * FROM "+TB_NAME1;
        cursor = db.rawQuery(query,null);
    }
    else if(year.equals("IInd")){
        String query = "SELECT * FROM " +TB_NAME2;
        cursor = db.rawQuery(query,null);
    }
    if(cursor!=null)
         cursor.moveToFirst();
db.close();
    return cursor;

}
   public ArrayList<StudentM> getAllRecords(int subject,int year){
       ArrayList<StudentM> names = new ArrayList<>();
       SQLiteDatabase db = this.getWritableDatabase();
       String query;
       Cursor cursor = null;
       if(year ==0) {
           switch (subject) {
               case 0:
                   query = "SELECT * FROM " + TB_NAME1;
                   cursor = db.rawQuery(query, null);
                   if (cursor.moveToFirst()) {
                       do {
                           StudentM studentM = new StudentM();
                           studentM.setName(cursor.getString(1));
                           names.add(studentM);
                       } while (cursor.moveToNext());
                   }
                   break;

               case 1:
                   query = "SELECT * FROM " + TB_NAME2;
                   cursor = db.rawQuery(query, null);
                   if (cursor.moveToFirst()) {
                       do {
                           StudentM studentM = new StudentM();
                           studentM.setName(cursor.getString(1));
                           names.add(studentM);
                       } while (cursor.moveToNext());
                   }
                   break;
               case 2:
                   query = "SELECT * FROM " + TB_NAME3;
                   cursor = db.rawQuery(query, null);
                   if (cursor.moveToFirst()) {
                       do {
                           StudentM studentM = new StudentM();
                           studentM.setName(cursor.getString(1));
                           names.add(studentM);
                       } while (cursor.moveToNext());
                   }
                   break;
               case 3:
                   query = "SELECT * FROM " + TB_NAME4;
                   cursor = db.rawQuery(query, null);
                   if (cursor.moveToFirst()) {
                       do {
                           StudentM studentM = new StudentM();
                           studentM.setName(cursor.getString(1));
                           names.add(studentM);
                       } while (cursor.moveToNext());
                   }
                   break;
           }
       }
       cursor.close();
       db.close();
       return names;
   }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
 public boolean dropTable(String year){
     SQLiteDatabase db = this.getWritableDatabase();
     Boolean result = false;

     switch (year){
         case "Ist": db.delete(TB_NAME1,null,null);
                      result=true;
                     break;
         case "IInd":db.delete(TB_NAME2,null,null);
                     result=true;
                     break;
     }
     db.close();
     return result;

 }

    public void increment(int tbName, int s, String name, String in, int choice,String date) {
        int inc=0;
        switch (in){
            case "1":inc=1;
                    break;
            case "2":inc=2;
                     break;
            case "3":inc =3;
                     break;
        }
       SQLiteDatabase db = this.getWritableDatabase();
       //date column to be passed;
        Cursor cursor = null;
        ContentValues cv;
        try { if(tbName == 0)
        {switch (s) {
                case 0:
                    cursor = db.query(TB_NAME1, new String[]{T1STUDENT_ID, date}, " NAME = ?", new String[]{name}, null, null, null);
                    int count1 =0;
                    if(cursor.moveToFirst()) {
                      count1 = cursor.getInt(1);
                        count1=count1+inc;
                    }

                    cv = new ContentValues();
                    cv.put(subject, count1);
                    db.update(TB_NAME1, cv, " NAME = ?", new String[]{name});

                    break;
                case 1:
                    cursor = db.query(TB_NAME2, new String[]{T2STUDENT_ID, subject}, " NAME = ?", new String[]{name}, null, null, null);
                    int count2 =0;
                    if(cursor.moveToFirst()){
                        count2 = cursor.getInt(1);
                        count2=count2+inc;
                    }
                    cv = new ContentValues();
                    cv.put(subject, count2);
                    db.update(TB_NAME2, cv, " NAME = ?", new String[]{name});
                    break;
            case 2:
                cursor = db.query(TB_NAME3, new String[]{T3STUDENT_ID, subject}, " NAME = ?", new String[]{name}, null, null, null);
                 count1 =0;
                if(cursor.moveToFirst()) {
                    count1 = cursor.getInt(1);
                    count1=count1+inc;
                }

                cv = new ContentValues();
                cv.put(subject, count1);
                db.update(TB_NAME3, cv, " NAME = ?", new String[]{name});

                break;
            case 3:
                cursor = db.query(TB_NAME4, new String[]{T4STUDENT_ID, subject}, " NAME = ?", new String[]{name}, null, null, null);
                count1 =0;
                if(cursor.moveToFirst()) {
                    count1 = cursor.getInt(1);
                    count1=count1+inc;
                }

                cv = new ContentValues();
                cv.put(subject, count1);
                db.update(TB_NAME4, cv, " NAME = ?", new String[]{name});

                break;

            }}
            else   //second yr
        {
        }
        }catch (SQLiteException e){

        }

cursor.close();
        db.close();


    }




    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLiteException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }

}