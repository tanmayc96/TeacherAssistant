package com.example.dellpctc.teacherassistant;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
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
              Intent  intent = new Intent(getActivity(),GetClass.class);
                getActivity().startActivity(intent);
            }
        });

      AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

}
