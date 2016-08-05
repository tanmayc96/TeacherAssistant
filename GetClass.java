package com.example.dellpctc.teacherassistant;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GetClass extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_class);
        ArrayAdapter<Year> listAdapter =new ArrayAdapter<Year>(this,android.R.layout.simple_expandable_list_item_1,Year.years);

        ListView listView = getListView();
        listView.setAdapter(listAdapter);

    }

   public void onListItemClick(ListView listView, View view, final int position, final long id) {
   /*     */
       final int[] pos = new int[1];
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setTitle("Which class?");
       builder.setMultiChoiceItems(R.array.Class, null, new DialogInterface.OnMultiChoiceClickListener() {


           @Override
           public void onClick(DialogInterface dialog, int which, boolean isChecked) {
               if (isChecked) {
                    pos[0] = which;
               }
           }
       });
       builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               int p= pos[0];
               Intent intent = new Intent(GetClass.this, ListSubjects.class);
               intent.putExtra(ListSubjects.Year, (int) id);
               intent.putExtra(ListSubjects.Choice,p );
               startActivity(intent);
           }


       });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
           finish();
        }
    });
AlertDialog alertDialog = builder.create();
       alertDialog.show();
   }



}

