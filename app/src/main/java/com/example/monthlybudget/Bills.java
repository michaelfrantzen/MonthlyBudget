package com.example.monthlybudget;


import android.util.Log;

import com.google.gson.Gson;

public class Bills {
    private static final String TAG = "BillsClass";
    private String name;
    private long amount;

    public Bills(String name, long amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
    @Override
    public String toString(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        Log.d(TAG, json);
        return json;
    }
}
