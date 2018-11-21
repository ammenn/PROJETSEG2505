package com.example.rayold.everydayneeds.activities;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rayold.everydayneeds.R;

public class CustomListview extends ArrayAdapter<String> {

    private String[] services ;
    private Integer[] imgid ;
    private Activity context ;
    public CustomListview(Activity context,String[] services,Integer[] imgid) {
        super(context, R.layout.listview_layout , services);
        this.context=context ;
        this.imgid = imgid ;
        this.services = services ;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView ;
        ViewHolder viewHolder = null ;
        if(r==null){
            LayoutInflater layoutInflater = context.getLayoutInflater() ;
            r=layoutInflater.inflate(R.layout.listview_layout,null,true) ;
            viewHolder = new ViewHolder(r) ;
            r.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) r.getTag() ;
        }
        viewHolder.ivw.setImageResource(imgid[position]);
        viewHolder.tvw1.setText(services[position]);


        return r;
    }

    class ViewHolder{
        TextView tvw1 ;
        ImageView ivw ;

        ViewHolder(View v){
            tvw1 = (TextView) v.findViewById(R.id.textView8) ;
            ivw = (ImageView) v.findViewById(R.id.imageView) ;
        }
    }
}
