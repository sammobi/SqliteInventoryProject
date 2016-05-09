package com.homestudy.sqliteinventoryproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Simpalm on 5/8/16.
 */
public class ShowProductsAdapter extends RecyclerView.Adapter<ShowProductsAdapter.ViewHolder> {

    private List<ProductModel> lstProductModel;
    private Context context;

    public ShowProductsAdapter(Context context) {
        this.context = context;


    }

    public void setList(List<ProductModel> lst) {
        lstProductModel = lst;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_showproduct, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductModel productModel = lstProductModel.get(position);
        holder.nameTv.setText(productModel.name);
        holder.priceTv.setText(productModel.price);
        holder.codeTv.setText(productModel.code);


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTv, priceTv, codeTv;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            priceTv = (TextView) itemView.findViewById(R.id.price_tv);
            codeTv = (TextView) itemView.findViewById(R.id.code_tv);


        }
    }

}
