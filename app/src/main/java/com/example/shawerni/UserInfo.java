package com.example.shawerni;

import android.content.Context;
import android.content.SharedPreferences;

public class UserInfo {
    private static final String PREF_NAME = "userinfo";
    private static final int KEY_IMAGENAME = 0;
    private static final String KEY_CER = "";

    private static final String KEY_NAME = "name";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public UserInfo(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setKeyImagename(int username){
        editor.putInt( String.valueOf( KEY_IMAGENAME ), username);
        editor.apply();
    }

    public void setName(String name){
        editor.putString(KEY_NAME, name);
        editor.apply();
    }
    public void setCer(String cer){
        editor.putString(KEY_CER, cer);
        editor.apply();
    }
    public void clearUserInfo(){
        editor.clear();
        editor.commit();
    }

    public int getKeyImagename(){return (int) prefs.getInt( String.valueOf( KEY_IMAGENAME ), 0);}

    public String getKeyName(){return prefs.getString(KEY_NAME, "");}
    public String getKeyCer(){return prefs.getString(KEY_CER, "");}
}

