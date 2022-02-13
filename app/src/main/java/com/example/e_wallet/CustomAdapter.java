package com.example.e_wallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    ArrayList<expense_details> list;
    Context cntx;
    CustomAdapter(Context cntx,ArrayList<expense_details> list){
        this.cntx=cntx;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if(convertView==null){
           LayoutInflater inflater= (LayoutInflater) cntx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         convertView=  inflater.inflate(R.layout.list_item,parent,false);
       }
        ImageView picture;
        TextView content,amount,expNote;
        picture=convertView.findViewById(R.id.picture);
        if(!list.get(position).image.equals("")){
            Picasso.get().load(list.get(position).getImage()).into(picture);
        }else{
            picture.setImageResource(R.drawable.other);
        }
        content=convertView.findViewById(R.id.content1);
        amount=convertView.findViewById(R.id.amount);
        expNote=convertView.findViewById(R.id.expNote);
        content.setText(list.get(position).getCategory());
        amount.setText(list.get(position).getExpense());
        expNote.setText(list.get(position).getNote());

        return convertView;
    }
}
