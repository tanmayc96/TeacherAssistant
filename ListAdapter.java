package com.example.dellpctc.teacherassistant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dell pc TC on 6/30/2016.
 */
public class ListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<StudentM> studentM;
    ListAdapter(Context context,ArrayList<StudentM> studentMArrayList){
        this.context = context;
        this.studentM = studentMArrayList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return studentM.size();
    }

    @Override
    public Object getItem(int position) {
        return studentM.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StudentM sM = getStudent(position);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.row_layout,parent,false);}
        ((TextView)convertView.findViewById(R.id.studentName)).setText(sM.Name);
          CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.checkStudent);
          checkBox.setOnCheckedChangeListener(myCheckStudent);
        checkBox.setTag(position);
        checkBox.setChecked(sM.present);
        return convertView;
    }

    CompoundButton.OnCheckedChangeListener myCheckStudent = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getStudent((Integer)buttonView.getTag()).present = isChecked;
        }
    };
    ArrayList<StudentM> getPresent(){
        ArrayList<StudentM> sM = new ArrayList<>();
        for (StudentM s : studentM){
            if(s.present)
            sM.add(s);
        }
       return sM;
    }

     StudentM getStudent(int position) {
    return ((StudentM)getItem(position));
    }
}
