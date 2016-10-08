package com.example.ggladko97.razdor;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends Activity implements View.OnClickListener {


    ListView listviewMain, listViewLittle;
    private int operationIndex=0;
    String text;
    static final int READ_BLOCK_SIZE = 1000;
    private double savedPricePLU=0.0;


    Button btnOk,btnCancel,btnAdminSettings, buttonCancelPLU, buttonBackToTables;
    TextView tvResult;
    ProductsDatabase helper = new ProductsDatabase(this);
    AdminLoginHelper helper2 = new AdminLoginHelper(this);
    private double priceResultPLU=0;
    public static final Integer[] images = { R.drawable.ballantines,
            R.drawable.bacon, R.drawable.jbean, R.drawable.juice };//images
    ImageView iv1;
    public List<String> temp_list;
    private ArrayAdapter<String> arrayAdapter2;

    public double getPrice(){
        return priceResultPLU;
    }


    public void reset(){
        arrayAdapter2.clear();
        priceResultPLU=0;
        tvResult.setText(String.valueOf(priceResultPLU));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intentCheckIfTableCreated = getIntent();
        boolean flagIsAgain=intentCheckIfTableCreated.getBooleanExtra("isAgain",false);
        if(flagIsAgain==true){
            tvResult.setText(String.valueOf(savedPricePLU));

        }
        iv1=(ImageView)findViewById(R.id.imageView2);
        List<String> list1 = new ArrayList<>();
        list1.addAll(helper.getPriceAndTitle());
        Set<String> hs = new HashSet<>();
        hs.addAll(list1);
        list1.clear();
        list1.addAll(hs);
        iv1.setImageResource(R.drawable.ballantines);

        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                list1 );

        listviewMain = (ListView)findViewById(R.id.leftList);
        listViewLittle = (ListView)findViewById(R.id.listViewVheck);
        btnCancel = (Button)findViewById(R.id.idCancel);
        btnOk = (Button)findViewById(R.id.idOk);
        buttonCancelPLU = (Button)findViewById(R.id.buttonCancelPLU);
        buttonBackToTables = (Button)findViewById(R.id.buttonBackToTables);

        tvResult = (TextView)findViewById(R.id.txtResult);
        btnAdminSettings = (Button)findViewById(R.id.buttonAdminset);
        btnAdminSettings.setOnClickListener(this);
        buttonBackToTables.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        buttonCancelPLU.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        listviewMain.setAdapter(arrayAdapter);
        temp_list = new ArrayList<String>();
        arrayAdapter2 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                temp_list );


        listviewMain.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList =(String) listviewMain.getItemAtPosition(position);
                temp_list.add(selectedFromList);
                if(helper.ifExists(selectedFromList)==true){
                    Toast.makeText(MainActivity.this,"Product does not exist",Toast.LENGTH_SHORT).show();
                }
                float res =  helper.getPrice(selectedFromList);

                checkListItem(selectedFromList);
                priceResultPLU+=res;
                tvResult.setText(String.valueOf(priceResultPLU));

                listViewLittle.setAdapter(arrayAdapter2);

            }
        });
    listViewLittle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String selectedFromList =(String) listViewLittle.getItemAtPosition(position);
            checkListItem(selectedFromList);
        }
    });
    }
    public void checkListItem(String objSelected){
        if(objSelected.equals("Potato")){
            iv1.setImageResource(R.drawable.potato);

        }
        else if(objSelected.equals("Glintvejn")){
            iv1.setImageResource(R.drawable.glintvejn);

        }
        else if(objSelected.equals("Ketchup")){
            iv1.setImageResource(R.drawable.ketchup);

        }else if(objSelected.equals("Bacon")){
            iv1.setImageResource(R.drawable.bacon);

        }else if(objSelected.equals("Ballantines")){
            iv1.setImageResource(R.drawable.ballantines);

        }else if(objSelected.equals("Juice")){
            iv1.setImageResource(R.drawable.juice);

        }
        else if(objSelected.equals("Wodka")){
            iv1.setImageResource(R.drawable.wodka);
        }
        else
        {
            //iv1.setImageResource(null);

        }
    }
    public Boolean write(String fname, String fcontent){
        try {

            String fpath = "/sdcard/"+fname+".txt";

            File file = new File(fpath);

            // If file does not exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw,256);

            bw.write(fcontent);
            Toast.makeText(MainActivity.this,"Check has been written to a file",Toast.LENGTH_SHORT).show();

            bw.close();

            Log.d("Suceess","Sucess");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first

        // Save the note's current draft, because the activity is stopping
        // and we want to be sure the current note progress isn't lost.
        ContentValues values = new ContentValues();
        values.put("table_prices", priceResultPLU);


//        getContentResolver().update(
//                null,   // The URI for the note to update.
//                values,  // The map of column names and new values to apply to them.
//                null,    // No SELECT criteria are used.
//                null     // No WHERE columns are used.
//        );
    }

    @Override
    protected void onRestart() {
        super.onRestart();  // Always call the superclass method first

        // Activity being restarted from stopped state
    }
    public String read(String fname){

        BufferedReader br = null;
        String response = null;

        try {

            StringBuffer output = new StringBuffer();
            String fpath = "/sdcard/"+fname+".txt";

            br = new BufferedReader(new FileReader(fpath));
            String line = "";
            while ((line = br.readLine()) != null) {
                output.append(line +"n");
            }
            response = output.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
        return response;

    }
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
    Date now = new Date();
    String fileName = formatter.format(now);//like 2016_01_12.txt
   /* public void writeToFile(String sBody){
        try {
            FileOutputStream fileout=openFileOutput(fileName, MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write("Operation #"+operationIndex+"\n");
            outputWriter.write("\n"+sBody);

            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    /*public String readFromFile(){
        String s="";

        try {
            FileInputStream fileIn=openFileInput("mytextfile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];

            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            //Toast.makeText(getBaseContext(), s,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;

        //Toast.makeText(MainActivity.this, "text returned",Toast.LENGTH_SHORT).show();

//result=tetx

    }*/
    @Override
    public void onClick(View v) {
        Intent intent;


        switch (v.getId()){
            case R.id.idOk:

                operationIndex++;
                helper.getWritableDatabase();
                for(int i=0;i<temp_list.size();i++){
                   write(fileName,temp_list.get(i));
                    //Toast.makeText(MainActivity.this,helper.decreaseQuantity(temp_list.get(i)),Toast.LENGTH_SHORT).show();

                }
                tvResult.setText(String.valueOf(priceResultPLU));
//                arrayAdapter2.clear();
                Intent i  = new Intent(MainActivity.this,TableActivity.class);
                i.putExtra("Price",priceResultPLU);

//                startActivity(i);
//                priceResultPLU=0;
                setResult(RESULT_OK, i);
                savedPricePLU=priceResultPLU;

                finish();




                break;
            case R.id.idCancel://todo
                Intent i1 = new Intent(MainActivity.this, AddActivity.class);
                startActivity(i1);
                break;

            case R.id.buttonAdminset:
                //Toast.makeText(MainActivity.this,"IDOK works !",Toast.LENGTH_LONG).show();
                intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonCancelPLU:
                reset();
                break;
            case R.id.buttonBackToTables:
                goBackToTables();
                break;

        }


    }

    private void goBackToTables() {
        Intent i  = new Intent(MainActivity.this,TableActivity.class);
        i.putExtra("Price",priceResultPLU);
        setResult(RESULT_OK, i);
        savedPricePLU=priceResultPLU;
        finish();

    }

}
