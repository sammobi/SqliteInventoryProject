package com.homestudy.sqliteinventoryproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends Activity {

    EditText mItemNameEt, mItemDescriptionEt, mItemQuantityEt, mItemPriceEt, mItemCodeEt;
    Button mAddItemBtn;

    String itemName, itemDescription, itemCode;
    int itemQuantity, itemPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mItemNameEt = (EditText) findViewById(R.id.etItemName);
        mItemDescriptionEt = (EditText) findViewById(R.id.etItemDescription);
        mItemQuantityEt = (EditText) findViewById(R.id.etNumberOfQuantity);
        mItemPriceEt = (EditText) findViewById(R.id.etItemPrice);
        mItemCodeEt = (EditText) findViewById(R.id.etItemCode);


        mAddItemBtn = (Button) findViewById(R.id.btnAddToInventory);

        mAddItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                itemName = mItemNameEt.getText().toString().trim();
                itemDescription = mItemDescriptionEt.getText().toString().trim();
                itemCode = mItemCodeEt.getText().toString().trim();
                if (mItemQuantityEt.equals("")) {
                    itemQuantity = 0;

                } else {

                    itemQuantity = Integer.parseInt(mItemQuantityEt.getText().toString().trim());
                }

                if (mItemPriceEt.equals("")) {
                    itemPrice = 0;

                } else {

                    itemPrice = Integer.parseInt(mItemPriceEt.getText().toString().trim());
                }

            }
        });


    }
}
