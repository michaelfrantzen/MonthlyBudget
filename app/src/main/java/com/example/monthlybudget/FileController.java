package com.example.monthlybudget;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class FileController {

    private static final String TAG = "FileController";
    private static final String BUDGET_FILE_TXT = "budgetFile.txt";
    private static final String BILLS_FILE_TXT = "billsFile.txt";
    private static final String EXPENSES_FILE_TXT = "expenses.txt";

    public boolean filesExist(Context context){
        if(this.readBudget(context) != null && this.readBills(context) != null){
            return true;
        }else{
            return false;
        }
    }

    public boolean writeBudget(Context context, long startingBudget){
        Budget budget = new Budget(startingBudget);
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(BUDGET_FILE_TXT, Context.MODE_PRIVATE));
            outputStreamWriter.write(budget.toString());
            outputStreamWriter.close();
            Log.d(TAG, "Wrote to file");
        } catch (Exception e) {
            Log.e(TAG, "Failed to Write to file");
            return false;
        }
        return true;

    }

    public boolean writeBills(Context context, List<Bills> billsList){
        Gson gson = new Gson();
        String json = gson.toJson(billsList);
        try{
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(BILLS_FILE_TXT, Context.MODE_PRIVATE));
            outputStreamWriter.write(json);
            outputStreamWriter.close();
            Log.d(TAG, "Wrote to file");
        }catch (Exception e){
            Log.e(TAG, "Failed to Write to file");
            return false;
        }
        return true;
    }

    public boolean writeExpenses(Context context, List<Expenses> expensesList){
        Gson gson = new Gson();
        String json = gson.toJson(expensesList);
        try{
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(EXPENSES_FILE_TXT, Context.MODE_PRIVATE));
            outputStreamWriter.write(json);
            outputStreamWriter.close();
            Log.d(TAG, "Wrote to file");
        }catch (Exception e){
            Log.e(TAG, "Failed to Write to file");
            return false;
        }
        return true;
    }

    public String readExpenses(Context context){
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(EXPENSES_FILE_TXT);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
                Log.d(TAG, ret);
            }
        }
        catch (Exception e) {
            Log.e(TAG, "Failed to read file");
            return null;
        }
        Log.d(TAG, "Read file");
        return ret;
    }

    public String readBills(Context context){
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(BILLS_FILE_TXT);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
                Log.d(TAG, ret);
            }
        }
        catch (Exception e) {
            Log.e(TAG, "Failed to read file");
            return null;
        }
        Log.d(TAG, "Read file");
        return ret;
    }


    public String readBudget(Context context){
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(BUDGET_FILE_TXT);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (Exception e) {
            Log.e(TAG, "Failed to read file");
            return null;
        }
        Log.d(TAG, "Read file");
        return ret;
    }
}
