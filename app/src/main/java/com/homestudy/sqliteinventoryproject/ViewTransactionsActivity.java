package com.homestudy.sqliteinventoryproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;


public class ViewTransactionsActivity extends Activity {

    ListView lv;
    List<TransactionModel> lst;


    SQLiteAdapter sqLiteAdapter;
    TransactionAdapter transactionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transactions);

        lv = (ListView) findViewById(R.id.lvTransactions);

        sqLiteAdapter = new SQLiteAdapter(this);

        lst = sqLiteAdapter.getAllTransaction();
        if (lst.size() > 0) {
            transactionAdapter = new TransactionAdapter(this, lst);

            lv.setAdapter(transactionAdapter);

        }


    }
}
