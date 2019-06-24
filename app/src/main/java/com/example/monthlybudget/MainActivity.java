package com.example.monthlybudget;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final FileController fc = new FileController();
    private static final boolean shouldAllowBack = false;

    private TextView mRemainingBudget;
    private TextView mWeeklyBudget;
    private ImageButton mOverviewButton;
    private Button mAddExpenseButton;
    private TextView mStartingMonthly;
    private TextView mStartingWeekly;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(bundle) called");
        setContentView(R.layout.activity_main);

        mRemainingBudget = findViewById(R.id.remaining_budget_TextView);
        mWeeklyBudget = findViewById(R.id.remaining_weekly_budget);
        mOverviewButton = findViewById(R.id.spending_overview);
        mAddExpenseButton = findViewById(R.id.add_expense);
        mStartingMonthly = findViewById(R.id.starting_budget_TextView);
        mStartingWeekly = findViewById(R.id.starting_weekly_budget_TextView);

        Gson gson = new Gson();
        Budget budget = gson.fromJson(fc.readBudget(getBaseContext()), Budget.class);
        Log.d(TAG, budget.toString());
        Type listType = new TypeToken<List<Bills>>(){}.getType();
        List<Bills> bills = gson.fromJson(fc.readBills(getBaseContext()), listType);

        long sumBills = 0;
        for (Bills bill : bills){
            sumBills += bill.getAmount();
        }

        budget.setRemainingBudget(budget.getStartBudget() - sumBills);
        mRemainingBudget.setText(budget.getRemainingBudget() + "");
        mWeeklyBudget.setText(budget.getWeeklyBudget() + "");
        mStartingMonthly.setText(budget.getRemainingBudget() + "");
        mStartingWeekly.setText(budget.getWeeklyBudget()+ "");

        Log.d(TAG, budget.toString());

        String expensesString = fc.readExpenses(getBaseContext());
        Log.d(TAG, expensesString);
        if(!expensesString.equals("null")){
            Log.d(TAG, expensesString);
            listType = new TypeToken<List<Expenses>>(){}.getType();
            List<Expenses> expenses = gson.fromJson(fc.readExpenses(getBaseContext()), listType);
            long sumExpenses = 0;
            for (Expenses exp : expenses){
                sumExpenses += exp.getAmount();
            }
            budget.setRemainingWeeklyBudget(budget.getWeeklyBudget() - sumExpenses);
            mWeeklyBudget.setText(budget.getRemainingWeeklyBudget() + "");
            budget.setRemainingBudget(budget.getRemainingBudget() - sumExpenses);
            mRemainingBudget.setText(budget.getRemainingBudget() + "");
        }


        mOverviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, OverviewActivity.class);
                MainActivity.this.startActivity(i);

            }
        });

        mAddExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddExpenseActivity.class);
                MainActivity.this.startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (!shouldAllowBack) {

        } else {
            super.onBackPressed();
        }
    }
}
