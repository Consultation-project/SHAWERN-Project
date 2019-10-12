package com.example.shawerni;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class MySharedPreference {
    //2
    private  static SharedPreferences prf;


    //1
    private MySharedPreference(){

    }

    //3
    public  static SharedPreferences getInstance(Context context){
        if(prf==null){
            prf=context.getSharedPreferences(Constance.key.USER_DETAIL,MODE_PRIVATE);
        }
        return prf;
    }

    //clear all shared pref
    public static void clearData(Context context){
        SharedPreferences.Editor editor=getInstance(context).edit();
        editor.clear();
        editor.commit();

    }

    public static void clearValue(Context context,String key){
        SharedPreferences.Editor editor=getInstance(context).edit();
        editor.remove(key);
        editor.commit();
    }

    public static void putString(Context context,String key ,String value){
        SharedPreferences.Editor editor=getInstance(context).edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static void putInt(Context context,String key ,int value){
        SharedPreferences.Editor editor=getInstance(context).edit();
        editor.putInt(key,value);
        editor.commit();}


    public static void putBoolean(Context context,String key ,boolean value){
        SharedPreferences.Editor editor=getInstance(context).edit();
        editor.putBoolean(key,value);
        editor.commit();}



    public static void putLong(Context context,String key ,Long value){
        SharedPreferences.Editor editor=getInstance(context).edit();
        editor.putLong(key,value);
        editor.commit();}


    public static String getString(Context context, String key , String valueDefault){
        return getInstance(context).getString(key,valueDefault);  }

    public static int getInt(Context context,String key ,int valueDefault){
        return getInstance(context).getInt(key,valueDefault);  }


    public static boolean getBoolean(Context context,String key ,boolean valueDefault){
        return getInstance(context).getBoolean(key,valueDefault);  }


    public static float getFloat(Context context,String key ,float valueDefault){
        return getInstance(context).getFloat(key,valueDefault);     }

}//end class

