package com.example.shawerni;

import android.content.Context;
import android.content.SharedPreferences;

public class UserInfo {
    private static final String PREF_NAME = "userinfo";
    private static final int KEY_IMAGENAME = 0;
    private static final String KEY_CER = "";
    private static final String KEY_PAID = "";
    private static final String KEY_STATUS = "";
    private static final String KEY_NAME = "";

    private static final String KEY_CONID = "name";
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

    public void setKeyConid(String conid){
        editor.putString(KEY_CONID, conid);
        editor.apply();
    }
    public void setKeyConName(String name){
        editor.putString(KEY_NAME, name);
        editor.apply();
}

    public void setMethod(String method){
        editor.putString(KEY_PAID, method);
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
    public String getKeyMethod(){return prefs.getString(KEY_PAID, "");}
    public String getKeyConId(){return prefs.getString(KEY_CONID, "");}

    public String getKeyConName(){return prefs.getString(KEY_NAME, "");}

    public String getKeyName(){return prefs.getString(KEY_CONID, "");}
    public String getKeyCer(){return prefs.getString(KEY_CER, "");}
}

