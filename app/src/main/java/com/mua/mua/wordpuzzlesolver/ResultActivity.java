package com.mua.mua.wordpuzzlesolver;

import android.Manifest;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class ResultActivity extends AppCompatActivity {



    AnimationDrawable animationDrawable;
    RelativeLayout relativeLayout;


    private TextView mainCurrentWord;

    private ArrayList<String> arrayList=new ArrayList<>();
    private ArrayAdapter<String> mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);




        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayoutAnother);
        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5500);
        animationDrawable.setExitFadeDuration(1000);




        TextView currentWord=findViewById(R.id.currentWord);
        mainCurrentWord=currentWord;


        ListView list;


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);


        mainAdapter=adapter;




        list = (ListView) findViewById(R.id.result);

        list.setAdapter(adapter);


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //getResult(Util.inputCharacters,Util.inputLength);
                //mainCurrentWord.setText("");

            }
        });



        Util.serial=0;
        Util.getResult(arrayList,mainAdapter,mainCurrentWord,Util.inputCharacters,Util.inputLength);
        mainCurrentWord.setText("--- "+arrayList.size()+" found ---");


    }




    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning())
            animationDrawable.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning())
            animationDrawable.stop();
    }



}
