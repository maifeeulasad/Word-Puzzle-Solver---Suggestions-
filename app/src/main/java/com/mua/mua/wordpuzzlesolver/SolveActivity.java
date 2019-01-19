package com.mua.mua.wordpuzzlesolver;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SolveActivity extends AppCompatActivity {


    AnimationDrawable animationDrawable;
    RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve);



        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5500);
        animationDrawable.setExitFadeDuration(1000);


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(
                            new InputStreamReader(getAssets().open("words.txt")));

                    String line;
                    while ((line = reader.readLine()) != null) {

                        ++Util.lineNumber;
                        Util.dictionary.put(line.toLowerCase(),true);

                    }
                } catch (IOException e) {

                    Util.loaded=false;

                } finally {
                    if (reader != null) {
                        Util.loaded=true;
                        try {
                            reader.close();



                        } catch (IOException e) {
                            //log the exception
                        }
                    }
                }




            }
        });


        final EditText inputCharacters=findViewById(R.id.inputCharacters);
        final Button button=findViewById(R.id.buttonGenerate);
        final EditText inputLength=findViewById(R.id.inputLength);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(Util.loaded==true){


                    Util.allSet=true;
                    try
                    {

                        Util.inputCharacters=inputCharacters.getText().toString().toLowerCase();
                        Util.inputLength=Integer.parseInt(inputLength.getText().toString());


                    }
                    catch (Exception e)
                    {
                        Toast.makeText(SolveActivity.super.getApplicationContext(),"Error!\nPlease enter valid inputs",Toast.LENGTH_LONG).show();
                        Util.allSet=false;

                    }

                    if(Util.allSet==true)
                    {

                        Intent i = new Intent(getBaseContext(), ResultActivity.class);
                        startActivity(i);
                    }


                }
                else
                {
                    Toast.makeText(SolveActivity.super.getApplicationContext(),"Please Wait!",Toast.LENGTH_SHORT).show();
                    /*
                    String parcent=new Double(Util.lineNumber /Util.totalLine).toString();

                    Toast.makeText(SolveActivity.super.getApplicationContext(),
                            "Dictionary loading --- "+  parcent.substring(0,parcent.indexOf("."))+
                            "%\n" ,
                            Toast.LENGTH_SHORT)
                                .show();
                                */
                }





            }
        });


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
