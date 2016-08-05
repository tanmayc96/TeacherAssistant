package com.example.dellpctc.teacherassistant;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DeleteDataFragmet extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_delete_data_fragmet, container, false);
       String[] names = new String[deleteHelper.deleteHelpers.length];
        for(int i=0;i<names.length;++i){
            names[i] = deleteHelper.deleteHelpers[i].getName();
        }
        Integer[] id = new Integer[deleteHelper.deleteHelpers.length];
        for(int i=0;i<id.length;++i){
            id[i]= deleteHelper.deleteHelpers[i].getId();
        }
        CaptionImagesAdapter adapter = new CaptionImagesAdapter(names,id);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.setListener(new CaptionImagesAdapter.listener() {
            @Override
            public void onClick(int position) {
                Intent intent;
                switch(position){
                    case 0: intent = new Intent(getActivity(),Resetdata.class);
                             startActivity(intent);
                             break;
                    case 1: intent = new Intent(getActivity(),DeleteData.class);
                        startActivity(intent);
                         break;
                    case 2: intent = new Intent(getActivity(),droptable.class);
                             startActivity(intent);
                }
            }
        });
       return recyclerView;
    }


}
