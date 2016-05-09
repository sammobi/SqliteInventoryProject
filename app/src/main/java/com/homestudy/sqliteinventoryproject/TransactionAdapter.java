package com.homestudy.sqliteinventoryproject;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simpalm on 2/24/16.
 */


public class TransactionAdapter extends BaseAdapter {

    /***********
     * Declare Used Variables
     *********/
    private Activity activity;
    private List<TransactionModel> data;
    private static LayoutInflater inflater = null;

    TransactionModel tempValues = null;
    int i = 0;

    /*************
     * CustomAdapter Constructor
     *****************/
    public TransactionAdapter(Activity a, List<TransactionModel> d) {

        /********** Take passed values **********/
        activity = a;
        data = d;


        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /********
     * What is the size of Passed Arraylist Size
     ************/
    public int getCount() {

        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /*********
     * Create a holder Class to contain inflated xml file elements
     *********/
    public static class ViewHolder {

        public TextView name, price, quantity, totalPrice;


    }

    /******
     * Depends upon data size called for each row , Create each ListView row
     *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.row_transaction, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.price = (TextView) vi.findViewById(R.id.price_tv);
            holder.quantity = (TextView) vi.findViewById(R.id.quantity_tv);
            holder.name = (TextView) vi.findViewById(R.id.name_tv);
            holder.totalPrice = (TextView) vi.findViewById(R.id.total_price_tv);

            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();


        /***** Get each Model object from Arraylist ********/
        tempValues = null;
        tempValues = (TransactionModel) data.get(position);

        /************  Set Model values in Holder elements ***********/

        holder.name.setText(tempValues.name);
        holder.quantity.setText(tempValues.quantity);
        holder.price.setText(tempValues.price);
        holder.totalPrice.setText(tempValues.totalPrice);

        /******** Set Item Click Listner for LayoutInflater for each row *******/


        return vi;
    }


}

