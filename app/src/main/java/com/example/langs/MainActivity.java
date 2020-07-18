package com.example.langs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void openNumbersList(View view){
        Toast.makeText(this,"Opening Numbers List",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,Numbers.class);
        startActivity(i);

    }
    public void openFamilyList(View view){
        Toast.makeText(this,"Opening Family's List",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,FamilyMembers.class);
        startActivity(i);
    }
    public void openColorsList(View view){
        Toast.makeText(this,"Opening Colors List",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,Colors.class);
        startActivity(i);
    }
    public void openPhrasesList(View view){
        Toast.makeText(this,"Opening Phrases List",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,Phrases.class);
        startActivity(i);
    }

    }

