package com.example.collagenup.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.collagenup.ObjectsAdapters.Obiekty;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflator;
    ArrayList<Obiekty> map;
    List<String> names;
    List<String> czas;
    List<String> zabieg;
    List<String> trwanie;

    public ItemAdapter (Context c , ArrayList<Obiekty> m)
    {
      mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      map = m;
      names = new ArrayList<String>();
      czas = new ArrayList<String>();
      zabieg = new ArrayList<String>();
      trwanie = new ArrayList<String>();
        for (Obiekty g: m) {
            names.add(g.nazwisko);
            czas.add(g.czas);
            zabieg.add(g.zabieg);
            trwanie.add(g.trwanie);
        }



    }

    @Override
    public int getCount()
    {
        return map.size();
    }

    public Object getItem(int position)
    {
        return names.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView (int position , View convertView , ViewGroup parent)
    {
        View v = mInflator.inflate(R.layout.item_layout, null);
        TextView nameTextView = (TextView) v.findViewById(R.id.textViewnazwisko);
        TextView priceTextView = (TextView) v.findViewById(R.id.textViewzabieg);
        TextView czasTextView = (TextView) v.findViewById(R.id.textViewczas);
        TextView trwanieTextView = (TextView) v.findViewById(R.id.textViewtrwanie);


        nameTextView.setText(names.get(position));
        priceTextView.setText(zabieg.get(position));
        czasTextView.setText(czas.get(position));
        trwanieTextView.setText(trwanie.get(position));
        return v;
    }



}
