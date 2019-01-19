package com.mua.mua.wordpuzzlesolver;

import android.content.Context;
import android.os.Debug;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Util {

    public static int serial=0;

    public static String inputCharacters="";
    public static int inputLength=0;

    public static int lineNumber=0;//466545
    public static boolean loaded=false;


    public static boolean allSet=false;


    //public static ArrayList<String> dictionary=new ArrayList<>();

    public static HashMap<String,Boolean> dictionary = new HashMap<>();
    public static int totalLine=466545;



    public static void getResult(ArrayList<String> arrayList, ArrayAdapter mainAdapter,TextView mainCurrentWord, String input, int n){


        if(input.length()<n)
            return ;

        arrayList.clear();


        List<List<Character>> combos = combinations( getChar(input),n);

        for (List<Character> x:
                combos) {

            permutation(arrayList,mainAdapter,mainCurrentWord,fromList(x));
        }



        arrayList = removeDuplicates(arrayList);
        Collections.sort(arrayList);




        mainAdapter.notifyDataSetChanged();


    }





    private static  ArrayList<String> removeDuplicates(ArrayList<String> list)
    {


        Log.d("remove duplicates","removing");

        ArrayList newList = new ArrayList<String>();

        for (String element : list) {

            if (!newList.contains(element)) {

                Log.d("remove duplicates","unique element");

                newList.add(new Integer(++Util.serial).toString()+".  "+element);
            }
        }

        return newList;
    }






    private static <T> List<List<T>> combinations( List<T> list, int n ){

        List<List<T>> result;

        if( list.size() <= n ){

            result = new ArrayList<List<T>>();
            result.add( new ArrayList<T>(list) );

        }else if( n <= 0 ){

            result = new ArrayList<List<T>>();
            result.add( new ArrayList<T>() );

        }else{

            List<T> sublist = list.subList( 1, list.size() );

            result = combinations( sublist, n );

            for( List<T> alist : combinations( sublist, n-1 ) ){
                List<T> thelist = new ArrayList<T>( alist );
                thelist.add( list.get(0) );
                result.add( thelist );
            }
        }

        return result;
    }

    private static List<Character> getChar(String data){
        List<Character> list = new ArrayList<Character>();

        int len=data.length();
        for(int i=0;i<len;i++){
            list.add(data.charAt(i));

        }
        return list;

    }

    private static String fromList(List<Character> x){

        String res="";

        for (Character y:
                x) {
            res+=y;
        }
        return res;
    }

    private static void permutation(ArrayList<String> arrayList,ArrayAdapter<String> mainAdapter,TextView mainCurrentWord,String str) {
        permutation(arrayList,mainAdapter,mainCurrentWord,"", str);
    }

    private static void permutation(ArrayList<String> arrayList,ArrayAdapter<String> mainAdapter,TextView mainCurrentWord,String prefix, String str) {
        int n = str.length();
        if (n == 0 ){

            mainCurrentWord.setText(prefix);

            if(Util.dictionary.containsKey(prefix))
            {

                //arrayList.add(new Integer(++Util.serial).toString()+".  "+prefix);
                arrayList.add(prefix);
                //mainAdapter.notifyDataSetChanged();
            }


        }
        else {
            for (int i = 0; i < n; i++)
                permutation(arrayList,mainAdapter,mainCurrentWord,prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }





}
