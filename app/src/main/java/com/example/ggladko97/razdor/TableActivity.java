package com.example.ggladko97.razdor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TableActivity extends Activity {
    ListView listViewTables;
    Intent temp;

//    List<String> tablesText;
    private int tablesCount;
//    List<Table> arrayTableAccounts;
//    List<Intent> arrayOfIntents;
    HashMap<Integer,Table> intentTableHashMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        listViewTables = (ListView) findViewById(R.id.listViewTables);
        //counting tables
        Intent intentTablesCounter = getIntent();
        tablesCount = intentTablesCounter.getIntExtra("TableCount", 0);
        Log.i("table count: ",String.valueOf(tablesCount));

        Intent intentPriceresultPLU = getIntent();
        double currentPriceResulPLU=intentPriceresultPLU.getDoubleExtra("Price",0.0);


        intentTableHashMap = new HashMap<>(tablesCount);
        Log.i("initializing hashtab","start OK");
        for (int k = 1; k <= tablesCount; k++) {
            Log.i("initializing hashtab","start OK");
            Table temp = new Table((short)k,0,true,0,0.0, new Intent());
            Log.i("initializing hashtab","tempTable OK");
            intentTableHashMap.put(k,temp);
            Log.i("initializing hashtab","puting to hash OK");
        }
        List<String> tableNames=new ArrayList<>();
        for (int i=1;i<=intentTableHashMap.size();i++){
            tableNames.add("Table# "+i);
        }

        //creating adapter for adding table names to list
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,tableNames
                );
        listViewTables.setAdapter(arrayAdapter);

        /*
        *array of Intents saves intents
         */
        listViewTables.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int realPosition=position+1;
                temp=intentTableHashMap.get(realPosition).getIntent();
                Table tempTable=intentTableHashMap.get(realPosition);
                Intent flag = new Intent(TableActivity.this,MainActivity.class);

                if(new Intent.FilterComparison(temp).equals(new Intent.FilterComparison(flag))){
                    startActivityForResult(temp,1);

                    if(true) {
                        Log.i("insider2", "setting color second time");
                        view.setBackgroundColor(Color.RED);
                    }
                }
                else{
                    temp = new Intent(TableActivity.this,MainActivity.class);
                    temp.putExtra("Position",position+1);
                    tempTable.setIntent(temp);
                    intentTableHashMap.put(realPosition,tempTable);
                    startActivityForResult(temp,1);


                    if(true) {
                        Log.i("insider1", "setting color First time");
                        view.setBackgroundColor(Color.GREEN);
                    }
//                    temp = new Intent(TableActivity.this,MainActivity.class);
//                    tempTable.setIntent(temp);
//                    intentTableHashMap.put(realPosition,tempTable);

                }



            }
        });




    }
    public boolean areEqual(Intent a, Intent b) {
        if(a.getIntExtra("Position",0)==b.getIntExtra("Position",0)){
            return true;
        }
        else {
            return  false;
        }

    }
}
