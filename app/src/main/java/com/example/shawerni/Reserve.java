package com.example.shawerni;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START post_class]
@IgnoreExtraProperties
public class Reserve {


    public String ID_Number;

    /**
     *
     */
    public String userId;
    public String conName;
    public String evDate;
    public String evtime;
    public String status;
    public String appointconnection;


    public String amount;
    public Map<String, Boolean> stars = new HashMap<>();
    public Reserve() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }
    public Reserve(String userId, String evDate, String evtime, String ID, String status,String conname,String appointconnection) {

        // public Reserve(String userId, String evDate, String evtime, String ID, String status,String conname,String appointconnection)

        this.ID_Number = ID;

        this.evDate = evDate;
        this.evtime = evtime;
        this.userId = userId;
        this.status = status;
        this.conName = conname;
        this.appointconnection = appointconnection;


    }


    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("ID_Number", ID_Number);
        result.put("status", status);
        result.put("evDate", evDate);
        result.put("conName",conName);
        result.put("evtime", evtime);
        result.put("userId", userId);
        result.put("appointconnection", appointconnection);

        return result;
    }


    // [END post_to_map]

}
// [END post_class]
