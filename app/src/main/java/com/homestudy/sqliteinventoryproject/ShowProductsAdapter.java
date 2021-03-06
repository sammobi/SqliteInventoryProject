package com.homestudy.sqliteinventoryproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Simpalm on 5/8/16.
 */
public class ShowProductsAdapter extends RecyclerView.Adapter<ShowProductsAdapter.ViewHolder> {

    private List<ProductModel> lstProductModel;
    private Context context;

    public ShowProductsAdapter(Context context, List<ProductModel> lst) {
        this.context = context;
        lstProductModel = lst;


    }

    public void setList(List<ProductModel> lst) {
        lstProductModel = lst;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_showproduct, parent, false);

        ViewHolder v = new ViewHolder(view);
        return v;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final ProductModel productModel = lstProductModel.get(position);
        holder.nameTv.setText(productModel.name);
        holder.priceTv.setText(productModel.price);
        holder.codeTv.setText(productModel.code);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked " + productModel.name, Toast.LENGTH_SHORT).show();


            }
        });


    }

    @Override
    public int getItemCount() {
        return lstProductModel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTv, priceTv, codeTv;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            priceTv = (TextView) itemView.findViewById(R.id.price_tv);
            codeTv = (TextView) itemView.findViewById(R.id.code_tv);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.row_rl);


        }
    }

}
