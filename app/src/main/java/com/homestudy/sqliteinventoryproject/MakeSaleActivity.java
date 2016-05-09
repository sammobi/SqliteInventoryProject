package com.homestudy.sqliteinventoryproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MakeSaleActivity extends Activity implements AdapterView.OnItemSelectedListener {

    Spinner mProductNameSpn;
    LinearLayout mDescriptionViewLl;
    TextView mItemDescriptionTv, mItemCodeTv, mPriceTv, mItemQuantityTv, mTotalPriceTv;
    Button mCalculateBtn;
    EditText mItemQuantityEt;
    SQLiteAdapter sqLiteAdapter;

    String name, price, quantity, code, description;


    List<ProductModel> lst = new ArrayList<>();
    List<String> lstProductName = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_sale);

        mProductNameSpn = (Spinner) findViewById(R.id.product_name_spn);
        mDescriptionViewLl = (LinearLayout) findViewById(R.id.item_description_ll);
        mItemDescriptionTv = (TextView) findViewById(R.id.item_description_tv);
        mItemCodeTv = (TextView) findViewById(R.id.item_code_tv);
        mPriceTv = (TextView) findViewById(R.id.item_price_tv);
        mItemQuantityTv = (TextView) findViewById(R.id.item_quantity_available_tv);
        mTotalPriceTv = (TextView) findViewById(R.id.total_price_tv);
        mCalculateBtn = (Button) findViewById(R.id.calculate_total_btn);
        mItemQuantityEt = (EditText) findViewById(R.id.enter_price_quantity_et);

        mProductNameSpn.setOnItemSelectedListener(this);
        sqLiteAdapter = new SQLiteAdapter(this);
        lst = sqLiteAdapter.getAllProduct();


        mCalculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String pricePerUnit = mPriceTv.getText().toString();


                final String quantityAvailable = mItemQuantityTv.getText().toString();
                final String requiredQuantity = mItemQuantityEt.getText().toString();
                if (requiredQuantity.length() <= 0) {

                    Toast.makeText(getApplicationContext(), "Required quantity cannot be less than or equal to zero", Toast.LENGTH_LONG).show();

                    return;
                }


                if (Integer.parseInt(quantityAvailable) < Integer.parseInt(requiredQuantity)) {

                    Toast.makeText(getApplicationContext(), "Please enter quantity equal to or less than" + quantityAvailable, Toast.LENGTH_LONG).show();
                    return;
                }


                final int totalPrice = Integer.parseInt(requiredQuantity) * Integer.parseInt(pricePerUnit);


                new AlertDialog.Builder(MakeSaleActivity.this)
                        .setTitle("Purchase Product")
                        .setMessage("You are going to purchase " + requiredQuantity + " which totals to" + "$ " + totalPrice)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                sqLiteAdapter.insertTransaction(name, price, requiredQuantity);


                                int remainingQuantity = (Integer.parseInt(quantityAvailable) - Integer.parseInt(requiredQuantity));
                                sqLiteAdapter.insertOrUpdateProduct(price, code, name, description, String.valueOf(remainingQuantity));

                                Intent intent = new Intent(MakeSaleActivity.this, MainActivity.class);
                                startActivity(intent);
                                MakeSaleActivity.this.finish();


                                // continue with delete
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        });

        lstProductName.add("Select Product Name");
// to store all the product name into the lstProductName
        for (ProductModel p : lst) {


            lstProductName.add(null != p.name ? p.name : "");

        }

        loadSpinnerData();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position == 0) {

            mDescriptionViewLl.setVisibility(View.GONE);


            return;
        }
// this is to get the selected product from the spinner
        ProductModel selectedProductModel = lst.get(position - 1);
        mDescriptionViewLl.setVisibility(View.VISIBLE);
        name = selectedProductModel.name;
        price = selectedProductModel.price;
        quantity = selectedProductModel.quantity;
        code = selectedProductModel.code;
        description = selectedProductModel.description;


        mItemDescriptionTv.setText(selectedProductModel.description.toString());
        mPriceTv.setText(selectedProductModel.price.toString());
        mItemCodeTv.setText(selectedProductModel.code.toString());
        mItemQuantityTv.setText(selectedProductModel.quantity.toString());


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void loadSpinnerData() {


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lstProductName);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mProductNameSpn.setAdapter(dataAdapter);
        mProductNameSpn.setSelection(0);

    }
}
