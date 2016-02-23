package com.homestudy.sqliteinventoryproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends Activity {

    EditText mItemNameEt, mItemDescriptionEt, mItemQuantityEt, mItemPriceEt, mItemCodeEt;
    Button mAddItemBtn;

    String itemName, itemDescription, itemCode;
    int itemQuantity = 0, itemPrice = 0;
    SQLiteAdapter sqLiteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mItemNameEt = (EditText) findViewById(R.id.etItemName);
        mItemDescriptionEt = (EditText) findViewById(R.id.etItemDescription);
        mItemQuantityEt = (EditText) findViewById(R.id.etNumberOfQuantity);
        mItemPriceEt = (EditText) findViewById(R.id.etItemPrice);
        mItemCodeEt = (EditText) findViewById(R.id.etItemCode);

        sqLiteAdapter = new SQLiteAdapter(this);


        mAddItemBtn = (Button) findViewById(R.id.btnAddToInventory);

        mAddItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                itemName = mItemNameEt.getText().toString().trim();
                itemDescription = mItemDescriptionEt.getText().toString().trim();
                itemCode = mItemCodeEt.getText().toString().trim();

                // checking if the field is not empty.
                if (!mItemPriceEt.getText().toString().isEmpty())

                    // converting the integer datatype to string value.
                    itemPrice = Integer.parseInt(mItemPriceEt.getText().toString().trim());
                if (!mItemQuantityEt.getText().toString().isEmpty())
                    itemQuantity = Integer.parseInt(mItemQuantityEt.getText().toString().trim());

                if (itemName.equals("")) {

                    mItemNameEt.setError("Please enter item name. ");

                    return;
                } else if (itemDescription.equals("")) {
                    mItemDescriptionEt.setError("Please enter item description. ");

                    return;
                } else if (itemCode.equals("")) {
                    mItemCodeEt.setError("Please enter item code. ");

                    return;


                } else if (itemPrice <= 0) {
                    mItemPriceEt.setError("Please enter item price. ");

                    return;
                } else if (itemQuantity <= 0) {
                    mItemQuantityEt.setError("Please enter item quantity. ");
                    return;

                } else {

// item price, itemquantity was an integer value but was converted to string.
                    long l = sqLiteAdapter.insertOrUpdateProduct(String.valueOf(itemPrice), itemCode, itemName, itemDescription, String.valueOf(itemQuantity));
                    if (l > 0) {

                        Toast.makeText(getApplicationContext(), "Successfully added product in the database", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Not able to add product in the database", Toast.LENGTH_LONG).show();


                    }


                }

            }


        });
    }
}







