package com.example.dellpctc.teacherassistant;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Edit extends Fragment {


    public Edit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_edit, container, false);
        String[] name = new String[editClass.editClasses.length];
        for(int i=0;i<name.length;++i){
            name[i] = editClass.editClasses[i].getName();
        }
        Integer[] id = new Integer[editClass.editClasses.length];
        for(int i=0;i<name.length;++i){
            id[i] = editClass.editClasses[i].getImgId();
        }
        CaptionImagesAdapter captionImagesAdapter = new CaptionImagesAdapter(name,id);
        recyclerView.setAdapter(captionImagesAdapter);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(linearLayoutManager);
        captionImagesAdapter.setListener(new CaptionImagesAdapter.listener() {
            Intent intent ;
            @Override
            public void onClick(int position) {
               switch(position){
                   case 0: intent = new Intent(getActivity(),DatePickerClass2.class);
                           getActivity().startActivity(intent);
               }
            }
        });
return recyclerView;    }

}
