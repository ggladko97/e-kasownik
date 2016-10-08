package com.example.ggladko97.razdor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends Activity implements View.OnClickListener{
    EditText etTitle, etVolume, etPriceperUnit, etQuantity;
    Button btnSUbmit, btnBack, btnGoTOMain;
    ProductsDatabase helper;
    private String txtTitle,txtVolume, txtPriceperUnit, txtQuantity;
    String tag="tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etTitle = (EditText)findViewById(R.id.txtTitle);
        etVolume = (EditText)findViewById(R.id.txtVolume);
        etPriceperUnit = (EditText)findViewById(R.id.txtPrice);
        etQuantity = (EditText)findViewById(R.id.txtQuantity);
        btnSUbmit = (Button)findViewById(R.id.btnSubmitAddingProduct);
        btnBack = (Button)findViewById(R.id.btnBack);
        btnGoTOMain = (Button)findViewById(R.id.buttonManage);
        btnBack.setOnClickListener(this);
        btnSUbmit.setOnClickListener(this);
        //btnGoTOMain.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnBack:
                Intent i = new Intent(AddActivity.this, LoginActivity.class);
                startActivity(i);
                break;
            case R.id.buttonManage:
                Intent ii = new Intent(AddActivity.this,MainActivity.class);
                startActivity(ii);
                break;
            case R.id.btnSubmitAddingProduct:
                helper = new ProductsDatabase(this);
                helper.getWritableDatabase();
                //hz
                Log.i(String.valueOf(tag),"Addactivity, getwritable database ok!");
                txtPriceperUnit = etPriceperUnit.getText().toString();
                txtQuantity = etQuantity.getText().toString();
                txtTitle = etTitle.getText().toString();
                txtVolume = etVolume.getText().toString();
                if(!helper.getTitle().equals(txtTitle)){
                    Products p = new Products();
                    p.setPrice(Float.parseFloat(txtPriceperUnit));
                    p.setQuantity(Integer.parseInt(txtQuantity));
                    p.setTitle(txtTitle);
                    p.setVolume(Integer.parseInt(txtVolume));
                    helper.insertProducts(p);
                    Log.i(String.valueOf(tag),"Addactivity, getwritable database ok!");
                    helper.close();
                    Toast.makeText(AddActivity.this,"Product has been succesfully added!",Toast.LENGTH_LONG).show();
                }else{
                    break;
                }

                break;

            default:
                break;
        }
    }
}
