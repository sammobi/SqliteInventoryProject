package com.homestudy.sqliteinventoryproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditItemActivity extends Activity implements AdapterView.OnItemSelectedListener {

    Spinner mItemNameSpn;
    EditText mItemNameEt, mItemDescriptionEt, mItemPriceEt, mItemCodeEt, mItemQuantityEt;

    LinearLayout mItemLayout;


    Button mUpdateItemBtn;

    String itemName, itemDescription, itemCode;
    int itemQuantity = 0, itemPrice = 0;
    SQLiteAdapter sqLiteAdapter;


    List<ProductModel> lst = new ArrayList<>();

    List<String> lstProductName = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);


        mItemNameSpn = (Spinner) findViewById(R.id.spnEditItemName);


        mItemNameEt = (EditText) findViewById(R.id.etViewItemName);
        mItemDescriptionEt = (EditText) findViewById(R.id.etViewItemDescription);
        mItemQuantityEt = (EditText) findViewById(R.id.etViewNumberOfQuantity);
        mItemPriceEt = (EditText) findViewById(R.id.etViewItemPrice);
        mItemCodeEt = (EditText) findViewById(R.id.etViewItemCode);
        mUpdateItemBtn = (Button) findViewById(R.id.btnUpdateItemDescription);

        mItemLayout = (LinearLayout) findViewById(R.id.liViewItemInformation);
        sqLiteAdapter = new SQLiteAdapter(this);
        lst = sqLiteAdapter.getAllProduct();

        mItemNameEt.setVisibility(View.GONE);

        lstProductName.add("Select Product Name");
// to store all the product name into the lstProductName
        for (ProductModel p : lst) {

            lstProductName.add(p.name);

        }

        loadSpinnerData();


        mItemNameSpn.setOnItemSelectedListener(this);

        // Loading spinner data from database
        // loadSpinnerData();

        mUpdateItemBtn.setOnClickListener(new View.OnClickListener() {
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


                if (itemDescription.equals("")) {
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

                        Intent intent = new Intent(EditItemActivity.this, MainActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Not able to add product in the database", Toast.LENGTH_LONG).show();


                    }
                }

            }

        });


    }

    private void loadSpinnerData() {


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lstProductName);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mItemNameSpn.setAdapter(dataAdapter);
        mItemNameSpn.setSelection(0);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position == 0) {

            mItemLayout.setVisibility(View.GONE);


            return;
        }
// this is to get the selected product from the spinner
        ProductModel selectedProductModel = lst.get(position - 1);
        mItemLayout.setVisibility(View.VISIBLE);
        mItemNameEt.setText(selectedProductModel.name.toString());
        mItemDescriptionEt.setText(selectedProductModel.description.toString());
        mItemPriceEt.setText(selectedProductModel.price.toString());
        mItemCodeEt.setText(selectedProductModel.code.toString());
        mItemQuantityEt.setText(selectedProductModel.quantity.toString());


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
