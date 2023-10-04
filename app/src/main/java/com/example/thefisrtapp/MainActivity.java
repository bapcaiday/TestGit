package com.example.thefisrtapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btnExit;
    private EditText txtColor;
    private LinearLayout myScreen;
    private TextView txtSpy;
    private final String PREFNAME="myFile1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this,"onCreate", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnExit=findViewById(R.id.btnExit);
        txtColor=(EditText)findViewById(R.id.txtMsg);
        myScreen=(LinearLayout)findViewById(R.id.myScreen);
        txtSpy=findViewById(R.id.txtSpy);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){ finish();}
        });

        txtColor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2){}

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable s) {
                String chosenName= s.toString().toLowerCase(Locale.US);
                txtSpy.setText(chosenName);
                setBackgroundColor(chosenName,myScreen);
            }
        });
    }

    private void setBackgroundColor(String chosen, LinearLayout myScreen){
        if (chosen.contains("huong")){
            myScreen.setBackgroundColor(0xfffab4f5);
        }
        if (chosen.contains("hue")){
            myScreen.setBackgroundColor(0xfff7ec68);
        }
        if (chosen.contains("nhan")){
            myScreen.setBackgroundColor(0xffe3bc9a);
        }
        if (chosen.contains("nam")){
            myScreen.setBackgroundColor(0xffdf46f3);
        }
        if (chosen.contains("thu")){
            myScreen.setBackgroundColor(0xff93c5f5);
        }

    }

    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(this,"onStart",Toast.LENGTH_SHORT).show();
        updateMeUsingSavedStateData();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(this,"onResume",Toast.LENGTH_SHORT).show();
    }

    private void updateMeUsingSavedStateData(){
        SharedPreferences myPrefContainer=getSharedPreferences(PREFNAME,Activity.MODE_PRIVATE);
        String key="ChosenBackgroundColor",defaultValue="white";
        if (myPrefContainer!=null && myPrefContainer.contains(key)){
            String color=myPrefContainer.getString(key,defaultValue);
            setBackgroundColor(color,myScreen);
        }
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Toast.makeText(this,"onRestart",Toast.LENGTH_SHORT).show();
    }

    private void saveStateData(String chosenColor){
        SharedPreferences myPrefContainer=getSharedPreferences(PREFNAME,Activity.MODE_PRIVATE);
        SharedPreferences.Editor myPrefEditor=myPrefContainer.edit();
        String key="ChosenBackgroundColor", value=txtSpy.getText().toString();
        myPrefEditor.putString(key,value);
        myPrefEditor.commit();
    }

    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(this,"onPause",Toast.LENGTH_SHORT).show();
        String chosenColor= txtSpy.getText().toString();
        saveStateData(chosenColor);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show();
    }
}