package com.example.dellpctc.teacherassistant;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Dell pc TC on 7/9/2016.
 */
public class CaptionImagesAdapter extends RecyclerView.Adapter<CaptionImagesAdapter.ViewHolder> {
    private String[] Name;
    private Integer[] imageId;
    private listener Listener;
    public static  interface listener {
        void onClick(int position);}
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.captioned_images_adapter,parent,false);
        return new ViewHolder(cv);
    }
    public CaptionImagesAdapter(String[] name, Integer[] id){
        this.Name=name;
        this.imageId=id;
    }

    public void setListener(listener listener) {
        Listener = listener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView)cardView.findViewById(R.id.Img);
        Drawable drawable = cardView.getResources().getDrawable(imageId[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(Name[position]);
        TextView textView =(TextView)cardView.findViewById(R.id.Info);
        textView.setText(Name[position]);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Listener!=null)
                Listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Name.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView View ) {
            super(View);
            cardView = View;
        }
    }
}
