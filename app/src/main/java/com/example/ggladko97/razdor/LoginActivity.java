package com.example.ggladko97.razdor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements View.OnClickListener {
    EditText txtLogin;
    TextView txtPasswd;
    Button btnLog_in,btnCreateUser;
    String stringLogin, stringPassword;
    AdminLoginHelper helper = new AdminLoginHelper(this);
    //private boolean checkPass = false;
    //private static final String PASS_CONST= "ggladko97";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtLogin = (EditText) findViewById(R.id.txtLogin);
        txtPasswd=(TextView) findViewById(R.id.txtPasswd);
        txtPasswd.setTransformationMethod(new HiddenPassTransformationMethod());

        btnLog_in = (Button)findViewById(R.id.btnLog_in);
        btnCreateUser = (Button)findViewById(R.id.btnCreateUser);


        btnCreateUser.setOnClickListener(this);
        btnLog_in.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLog_in:
                stringLogin = txtLogin.getText().toString();
                stringPassword = txtPasswd.getText().toString();
                String login = helper.searchPass(stringLogin);

                //login eto parol'
                if(stringPassword.equals(login)&&stringPassword.equals("79nisivi")){


                            Intent i = new Intent(LoginActivity.this, SettingsActivity.class);

                            startActivity(i);

                    }else if(stringPassword.equals(login)){


                    Intent i = new Intent(LoginActivity.this, SettingsActivity.class);
                    startActivity(i);
                }

                else{
                    Toast.makeText(LoginActivity.this, "username or password does not match",Toast.LENGTH_LONG).show();

                }

                break;
            case R.id.btnCreateUser:
                try{
                Intent i  = new Intent(LoginActivity.this,CreateActivity.class);
                startActivity(i);}
                catch (NullPointerException ex){
                    throw new NullPointerException("Create user failed");
                }finally {
                    break;
                }

        }

    }
    private class HiddenPassTransformationMethod implements TransformationMethod {

        private char DOT = '\u2022';

        @Override
        public CharSequence getTransformation(final CharSequence charSequence, final View view) {
            return new PassCharSequence(charSequence);
        }

        @Override
        public void onFocusChanged(final View view, final CharSequence charSequence, final boolean b, final int i,
                                   final Rect rect) {
            //nothing to do here
        }

        private class PassCharSequence implements CharSequence {

            private final CharSequence charSequence;

            public PassCharSequence(final CharSequence charSequence) {
                this.charSequence = charSequence;
            }

            @Override
            public char charAt(final int index) {
                return DOT;
            }

            @Override
            public int length() {
                return charSequence.length();
            }

            @Override
            public CharSequence subSequence(final int start, final int end) {
                return new PassCharSequence(charSequence.subSequence(start, end));
            }
        }
    }
}
