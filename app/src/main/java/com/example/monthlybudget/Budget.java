package com.example.monthlybudget;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Budget {
    private static final String TAG = "BudgetClass";
    private long startBudget;
    private long remainingBudget;
    private long weeklyBudget;
    private long remainingWeeklyBudget;

    public Budget(long startBudget){
        this.startBudget = startBudget;
        this.remainingBudget = Long.MIN_VALUE;
        this.remainingWeeklyBudget = this.weeklyBudget = startBudget / 4;
    }

    public long getStartBudget() {
        return startBudget;
    }

    public void setStartBudget(long startBudget) {
        this.startBudget = startBudget;
    }

    public long getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(long remainingBudget) {
        this.remainingBudget = remainingBudget;
        this.remainingWeeklyBudget = this.weeklyBudget = remainingBudget / 4;
    }

    public long getWeeklyBudget() {
        return weeklyBudget;
    }

    public void setWeeklyBudget(long weeklyBudget) {
        this.weeklyBudget = weeklyBudget;
    }

    public long getRemainingWeeklyBudget() {
        return remainingWeeklyBudget;
    }

    public void setRemainingWeeklyBudget(long remainingWeeklyBudget) {
        this.remainingWeeklyBudget = remainingWeeklyBudget;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        Log.d(TAG, json);
        return json;
    }
}
