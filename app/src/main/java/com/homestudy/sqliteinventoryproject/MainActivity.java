package com.homestudy.sqliteinventoryproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    Button mAddItem, mEditItem, mMakeSale, mViewTransactions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddItem = (Button) findViewById(R.id.btnAddItem);
        mEditItem = (Button) findViewById(R.id.btnEdit);
        mMakeSale = (Button) findViewById(R.id.btnSale);
        mViewTransactions = (Button) findViewById(R.id.btnTransaction);

        mAddItem.setOnClickListener(this);
        mEditItem.setOnClickListener(this);
        mMakeSale.setOnClickListener(this);
        mViewTransactions.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {

            case R.id.btnAddItem:

                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivity(intent);
                break;
            case R.id.btnEdit:

                Intent intent1 = new Intent(MainActivity.this, EditItemActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnSale:

                Intent intent2 = new Intent(MainActivity.this, MakeSaleActivity.class);
                startActivity(intent2);
                break;

            case R.id.btnTransaction:

                Intent intent3 = new Intent(MainActivity.this, ViewTransactionsActivity.class);
                startActivity(intent3);
                break;


        }

    }
}
