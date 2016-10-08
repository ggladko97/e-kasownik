package com.example.ggladko97.razdor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    Switch switchTableFeatureONof;
    EditText editTextCountTable;
    private int tablesCount=0;

    public int getTablesCount() {
        return tablesCount;
    }
    private void setTablesCount(int tablesCount){
        this.tablesCount = tablesCount;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // final String[] tempCountTable = new String[1];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchTableFeatureONof=(Switch)findViewById(R.id.switch1);
        editTextCountTable= (EditText)findViewById(R.id.counttableTV);
        switchTableFeatureONof.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    try{
                    setTablesCount(Integer.parseInt(editTextCountTable.getText().toString()));}
                    catch (NumberFormatException e){
                        Log.i("Exception",editTextCountTable.getText().toString());
                        e.printStackTrace();
                    }
                   //tablesCount =Integer.parseInt(editTextCountTable.getText().toString());
                    Intent i = new Intent(SettingsActivity.this, TableActivity.class);
                    i.putExtra("TableCount"
                            ,getTablesCount());

                    startActivity(i);
                }else{
                    editTextCountTable.setEnabled(false);
                }
            }
        });
    }
}
