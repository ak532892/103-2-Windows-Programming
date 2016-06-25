package com.example.student.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private Button jButton1;
    private EditText edit1;
    private EditText edit2;
    private EditText edit3;
    private Spinner spinner;
    private Spinner spinner0;
    private double rate;
    private List<String> cities = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private String mStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jButton1 = (Button) findViewById(R.id.button);
        edit1 = (EditText) findViewById(R.id.editText);
        edit2 = (EditText) findViewById(R.id.editText2);
        edit3 = (EditText) findViewById(R.id.editText3);

        jButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calculate();
            }
        });
        initDate();
    }

    private void initDate(){
        int s;
        spinner0 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter0=ArrayAdapter.createFromResource(this,R.array.money_array0,android.R.layout.simple_spinner_item);
        //樣式
        adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner0.setAdapter(adapter0);
        //選擇
        spinner0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //setRate((int) spinner0.getSelectedItemId());
                setRate((int) spinner0.getSelectedItemId(),(int) spinner.getSelectedItemId());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.money_array,android.R.layout.simple_spinner_item);
        //樣式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //選擇
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinner.getSelectedItemId() == 0)
                    Toast.makeText(MainActivity.this, spinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "您選擇" + spinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                setRate((int) spinner0.getSelectedItemId(),(int) spinner.getSelectedItemId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
            }
        });
    }/*
    public void setRate(int num) {
        switch (num) {
            case 1:
                rate = 0.19823;
                break;
            case 2:
                rate = 3.84007;
                break;
            case 3:
                rate = 0.03167;
                break;
            case 4:
                rate = 0.02123;
                break;
            case 5:
                rate = 0.24605;
                break;
            case 6:
                rate = 0.03180;
                break;
            case 7:
                rate = 0.02983;
                break;
            case 8:
                rate = 0.04109;
                break;
        }
    }
    */

    public void setRate(int sor,int dst){



        switch(sor){
            case 0:
                switch(dst){
                    case 1:
                        rate = 0.19823;
                        break;
                    case 2:
                        rate = 3.84007;
                        break;
                    case 3:
                        rate = 0.03167;
                        break;
                    case 4:
                        rate = 0.02123;
                        break;
                    case 5:
                        rate = 0.24605;
                        break;
                    case 6:
                        rate = 0.03180;
                        break;
                    case 7:
                        rate = 0.02983;
                        break;
                    case 8:
                        rate = 0.04109;
                        break;
                }
                break;
            case 1:
                switch(dst){
                    case 1:
                        rate = 0.19823/0.19823;
                        break;
                    case 2:
                        rate = 3.84007/0.19823;
                        break;
                    case 3:
                        rate = 0.03167/0.19823;
                        break;
                    case 4:
                        rate = 0.02123/0.19823;
                        break;
                    case 5:
                        rate = 0.24605/0.19823;
                        break;
                    case 6:
                        rate = 0.03180/0.19823;
                        break;
                    case 7:
                        rate = 0.02983/0.19823;
                        break;
                    case 8:
                        rate = 0.04109/0.19823;
                        break;
                }
                break;
            case 2:
                switch(dst){
                    case 1:
                        rate = 0.19823/0.03167;
                        break;
                    case 2:
                        rate = 3.84007/0.03167;
                        break;
                    case 3:
                        rate = 0.03167/0.03167;
                        break;
                    case 4:
                        rate = 0.02123/0.03167;
                        break;
                    case 5:
                        rate = 0.24605/0.03167;
                        break;
                    case 6:
                        rate = 0.03180/0.03167;
                        break;
                    case 7:
                        rate = 0.02983/0.03167;
                        break;
                    case 8:
                        rate = 0.04109/0.03167;
                        break;
                }
                break;
        }
        if( dst == 0 ) {
            edit2.setText(String.format(""));
            edit3.setText(String.format(""));
        }
        if( dst != 0 ) edit3.setText(String.format("1 : %.05f", rate));
    }

    public void Calculate(){
        try{
            if((int) spinner.getSelectedItemId() != 0){
                BigDecimal TWD = new BigDecimal(String.valueOf(edit1.getText()));
                BigDecimal result = new BigDecimal(Double.toString(rate));
                BigDecimal zero = new BigDecimal("0");
                result = result.multiply(TWD);
                if(result.compareTo(zero) == -1)
                    throw new Exception("error");
                edit2.setText("" + result.setScale(5, BigDecimal.ROUND_HALF_UP));
            }
        }
        catch (Exception ex){
            edit2.setText(String.format("error"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}