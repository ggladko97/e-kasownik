package com.example.ggladko97.razdor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends Activity implements View.OnClickListener {
    EditText txtName, txtSurname,txtLogin,txtPassword,txtRepeatPassword;
    Button btnAddUser, btnGoTo;
    AdminLoginHelper helper  = new AdminLoginHelper(this);

    private String stringName,stringSurname, stringuserName, stringPassword, stringRepeatPasword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        txtName = (EditText)findViewById(R.id.txtName);
        txtSurname = (EditText)findViewById(R.id.txtSurname);
        txtLogin = (EditText)findViewById(R.id.txtlogin);
        txtPassword = (EditText)findViewById(R.id.txtpasswd);
        txtRepeatPassword = (EditText)findViewById(R.id.txtrepeatPasswd);

        btnAddUser = (Button)findViewById(R.id.btnAddUser);
        btnGoTo = (Button)findViewById(R.id.btngotoNext);
        btnAddUser.setOnClickListener(this);
        btnGoTo.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddUser:
                stringName = txtName.getText().toString();
                stringSurname = txtSurname.getText().toString();
                stringuserName = txtLogin.getText().toString();
                stringPassword = txtPassword.getText().toString();
                stringRepeatPasword = txtRepeatPassword.getText().toString();
                if(stringPassword.equals(stringRepeatPasword)){
                    Contact contactNew = new Contact();
                    contactNew.setName(stringName);
                    contactNew.setSurname(stringSurname);
                    contactNew.setUsername(stringuserName);
                    contactNew.setPassword(stringPassword);
                    helper.insertContact(contactNew);
                    Toast.makeText(CreateActivity.this,"User has been succesfully added",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(CreateActivity.this,"Repeat password again",Toast.LENGTH_LONG).show();//check if repeat pasword matches password
                }
                break;
            case R.id.btngotoNext:
                Intent i  = new Intent(CreateActivity.this, LoginActivity.class);
                startActivity(i);
                break;


        }

    }
}
