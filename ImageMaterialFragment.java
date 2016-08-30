package com.example.dellpctc.teacherassistant;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageMaterialFragment extends Fragment {

    public ImageMaterialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.image_material,container,false);
        String[] names = new String[DisplayIcon.displayIcons.length];
        for(int i = 0; i<names.length;++i){
            names[i]= DisplayIcon.displayIcons[i].getDName();
        }
        Integer[] id = new Integer[DisplayIcon.displayIcons.length];
        for (int i=0;i<id.length;++i){
            id[i]=DisplayIcon.displayIcons[i].getDId();
        }
        CaptionImagesAdapter captionImagesAdapter = new CaptionImagesAdapter(names,id);
        recyclerView.setAdapter(captionImagesAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        captionImagesAdapter.setListener(new CaptionImagesAdapter.listener() {
            @Override
            public void onClick(int position) {
                Intent intent;
                switch (position){
                    case 0:  intent = new Intent(getActivity(),AddData.class);
                               getActivity().startActivity(intent);
                        break;
                    case 1: intent = new Intent(getActivity(),Main3Activity.class);
                             getActivity().startActivity(intent);
                             break;
                    case 2: /*intent = new Intent(getActivity(),GetClass.class);
                            getActivity().startActivity(intent);*/
                           date();
                           break;
                    case 3: intent = new Intent(getActivity(),AndroidDatabaseManager.class);//tableLayoutFile.java
                           getActivity().startActivity(intent);
                            break;
                    case 4: intent = new Intent(getActivity(),Upload.class);
                            getActivity().startActivity(intent);
                            break;
                    case 5: intent = new Intent(getActivity(),Main4Activity.class);
                        getActivity().startActivity(intent);
                        break;
                    case 6: intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getActivity().startActivity(intent);
                }
            }
        });

        return recyclerView;
    }
    public void date(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Do you want to set an  new date ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), DatePickerClass.class);
                 intent.putExtra(DatePickerClass.id,101); //
                getActivity().startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                  new setDate().execute();


            }
        });

      AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

     private class setDate extends AsyncTask<Void, Void, Boolean> {
           int day, month;
           ContentValues cv;
        int count=0;
        @Override
        protected void onPreExecute() {
            Calendar calendar = Calendar.getInstance();
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH)+1;

           cv= new ContentValues();
            cv.put("DAY",day);
            cv.put("MONTH",month);
            cv.put("FLAG",false);
        }

        @Override
        protected Boolean doInBackground(Void... params) {

           Calendar calendar = Calendar.getInstance();
              Boolean result= false;
            SQLiteOpenHelper sql = new dataBaseHandler(getActivity());

            try{
                   int dayO = calendar.get(Calendar.DAY_OF_MONTH);
                SQLiteDatabase db = sql.getWritableDatabase();

                 SQLiteDatabase db2 = sql.getReadableDatabase();
                Cursor mCursor = db2.rawQuery("SELECT * FROM DateKeeper",null);
                if(mCursor.moveToFirst()) {
                    count = mCursor.getCount();
                }
                if(count==0 || count <0)
                {
                      db.insert("DateKeeper", null, cv);
                      result=true;
                }
                else {
                    Cursor cursor = db2.query("DateKeeper", new String[]{"_id", "DAY"}, " _id = 1", null, null, null, null);
                    if (cursor.moveToFirst()) {
                        int day = cursor.getInt(1);
                        if (day != dayO){
                            db.update("dateKeeper",cv," _id = ?",new String[]{Integer.toString(1)});
                        }
                        result = true;
                    }
                }

               // db.insert("DateKeeper", null, cv);
                 //  result=true;

            }catch (SQLiteException e){
                Toast.makeText(getActivity(),"ERRROR",Toast.LENGTH_SHORT).show();
               result=false;
            }
            return result;
        }

         @Override
         protected void onPostExecute(Boolean aBoolean) {
             if(aBoolean){
                 Intent  intent = new Intent(getActivity(),GetClass.class);
                 getActivity().startActivity(intent);
             }
         }
     }

   /* public void setDate(){
        int day, month;
        ContentValues cv;
        int count = 0;
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        cv= new ContentValues();
        cv.put("DAY",day);
        cv.put("MONTH",month);
        cv.put("FLAG",false);

        SQLiteOpenHelper sql = new dataBaseHandler(getActivity());

        try{
            int dayO = calendar.get(Calendar.DAY_OF_MONTH);
            SQLiteDatabase db2 = sql.getReadableDatabase();
            SQLiteDatabase db = sql.getWritableDatabase();
            Cursor mCursor = db.rawQuery("SELECT COUNT(*) FROM DateKeeper",null);
            if(mCursor.moveToFirst()) {
                count = mCursor.getCount();
            }
            if(count<0){
                db.insert("DateKeeper", null, cv);
            }
            else {
                Cursor cursor = db2.query("DateKeeper", new String[]{"_id", "DAY"}, " _id = ?", new String[]{Integer.toString(1)}, null, null, null);
                if (cursor.moveToFirst()) {
                    int day2 = cursor.getInt(1);
                    if (day2 != dayO)
                        db.insert("DateKeeper", null, cv);
                }
            }
        }catch (SQLiteException e){
            Toast.makeText(getActivity(),"ERRROR",Toast.LENGTH_SHORT).show();
        }
        Intent  intent = new Intent(getActivity(),GetClass.class);
        getActivity().startActivity(intent);
    }*/
}
