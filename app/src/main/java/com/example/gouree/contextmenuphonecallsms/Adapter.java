package com.example.gouree.contextmenuphonecallsms;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by omsai on 20/09/2017.
 */

public class Adapter extends ArrayAdapter
{
    //create string of names and contact numbers

    Activity context;
    private String[] names ;
    private String[] contacts;

    LayoutInflater inflater;

    //constructor
    public Adapter(Activity context,String[] names,String[] contacts)
    {
        //specify the custom layout xml file
        super(context, R.layout.model_layout,names);
        this.context = context;
        this.names=names;
        this.contacts=contacts;

    }


    //vew holder class to hold views
    public class ViewHolder
    {
        TextView txnames;
        TextView txcontacts;

        ViewHolder(View v)
        {
            //store the textview values in the variables
            txnames= (TextView) v.findViewById(R.id.textView);
            txcontacts = (TextView)v.findViewById(R.id.textView2);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View r= convertView;
        ViewHolder viewholder =null;

        //if view is null use layout inflater to inflate
        if(r==null)
        {
            LayoutInflater layoutInflater =context.getLayoutInflater();
            r= layoutInflater.inflate(R.layout.model_layout,null,true);
            viewholder=new ViewHolder(r);
            r.setTag(viewholder);

        }
        else
        {
            viewholder = (ViewHolder) r.getTag();
        }

        //set values in textviews
        viewholder.txnames.setText(names[position]);
        viewholder.txcontacts.setText(contacts[position]);
        return r;
    }
}
