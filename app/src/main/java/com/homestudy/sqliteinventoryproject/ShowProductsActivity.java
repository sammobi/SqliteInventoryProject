package com.homestudy.sqliteinventoryproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShowProductsActivity extends Activity {

    RecyclerView recyclerView;
    SQLiteAdapter sqLiteAdapter;
    Context context;
    List<ProductModel> lst = new ArrayList<ProductModel>();


    ShowProductsAdapter showProductsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);

        context = this;
        sqLiteAdapter = new SQLiteAdapter(context);
        lst = sqLiteAdapter.getAllProduct();



        recyclerView = (RecyclerView) findViewById(R.id.product_rcv);
     //   recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new MarginDecoration(context));
        showProductsAdapter = new ShowProductsAdapter(context, lst);
        recyclerView.setAdapter(showProductsAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));



      //  showProductsAdapter.setList(lst);
    }
}
