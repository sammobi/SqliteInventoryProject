package com.homestudy.sqliteinventoryproject;

import android.app.Activity;
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

                String pricePerUnit = mPriceTv.getText().toString();


                String quantityAvailable = mItemQuantityTv.getText().toString();
                String requiredQuantity = mItemQuantityEt.getText().toString();


                if (Integer.parseInt(quantityAvailable) < Integer.parseInt(requiredQuantity)) {

                    Toast.makeText(getApplicationContext(), "Please enter quantity equal to or less than" + quantityAvailable, Toast.LENGTH_LONG).show();

                    return;


                }

                int totalPrice = Integer.parseInt(requiredQuantity) * Integer.parseInt(pricePerUnit);

                mTotalPriceTv.setText("" + totalPrice);


            }
        });

        lstProductName.add("Select Product Name");
// to store all the product name into the lstProductName
        for (ProductModel p : lst) {

            lstProductName.add(p.name);

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
        ProductModel selectedProductModel = lst.get(position);
        mDescriptionViewLl.setVisibility(View.VISIBLE);

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
