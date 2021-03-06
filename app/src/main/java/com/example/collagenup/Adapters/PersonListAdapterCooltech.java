package com.example.collagenup.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import com.example.collagenup.ObjectsAdapters.Person;
import com.example.myapplication.R;

import java.util.ArrayList;

public class PersonListAdapterCooltech  extends ArrayAdapter<Person> {


    private static final String TAG = "PersonListAdapterCooltech";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {

        TextView nazwisko ;
        TextView icoone_ilosc ;
        TextView icoone_suma ;
        TextView pierwszy_icoone ;
        TextView pierwszy_ost_pakiet;
        TextView ilosc_pakietow;
        TextView ilosc_gratisow;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public PersonListAdapterCooltech(Context context, int resource, ArrayList<Person> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String nazwisko = getItem(position).getNazwiskonazwisko();
        String icoone_ilosc = getItem(position).getIcoone_ilosc();
        String icoone_suma = getItem(position).getIcoone_suma();
        String pierwszy_icoone = getItem(position).getPierwszy_icoone();
        String pierwszy_ost_pakiet = getItem(position).getPierwszy_ost_pakiet();
        String ilosc_pakietow = getItem(position).getIlosc_pakietow();
        String ilosc_gratisow = getItem(position).getIlosc_gratisow();


        //Create the person object with the information
        Person person = new Person(nazwisko,icoone_ilosc,icoone_suma,pierwszy_icoone, pierwszy_ost_pakiet, ilosc_pakietow, ilosc_gratisow);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        PersonListAdapterCooltech.ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new PersonListAdapterCooltech.ViewHolder();
            holder.nazwisko = (TextView) convertView.findViewById(R.id.textView1);
            holder.icoone_ilosc = (TextView) convertView.findViewById(R.id.textView5);

            holder.icoone_suma = (TextView) convertView.findViewById(R.id.textView7);
            holder.pierwszy_icoone = (TextView) convertView.findViewById(R.id.textView2);
            holder.pierwszy_ost_pakiet = (TextView) convertView.findViewById(R.id.textView3);
            holder.ilosc_pakietow = (TextView) convertView.findViewById(R.id.textView4);
            holder.ilosc_gratisow = (TextView) convertView.findViewById(R.id.textViewGratis);


            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (PersonListAdapterCooltech.ViewHolder) convertView.getTag();
            result = convertView;
        }



        lastPosition = position;
/*
        holder.name.setText(person.getName());
        holder.birthday.setText(person.getBirthday());
        holder.sex.setText(person.getSex());
*/
        holder.nazwisko.setText(person.getNazwiskonazwisko());
        holder.icoone_ilosc.setText("Ilość zabiegów:" + person.getIcoone_ilosc());
        holder.icoone_suma.setText("");
        holder.pierwszy_icoone.setText("");
        holder.pierwszy_ost_pakiet.setText("");
        holder.ilosc_pakietow.setText("");
        holder.ilosc_gratisow.setText("");

        if (Integer.parseInt(person.getIlosc_gratisow())==0) holder.ilosc_gratisow.setVisibility(View.INVISIBLE);
        else holder.ilosc_gratisow.setVisibility(View.VISIBLE);


        holder.icoone_suma.setVisibility(View.INVISIBLE);
        holder.pierwszy_icoone.setVisibility(View.INVISIBLE);
        holder.pierwszy_ost_pakiet.setVisibility(View.INVISIBLE);
        holder.ilosc_pakietow.setVisibility(View.INVISIBLE);

        return convertView;
    }
}
