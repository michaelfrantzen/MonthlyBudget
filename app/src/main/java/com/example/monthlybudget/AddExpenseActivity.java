package com.example.monthlybudget;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddExpenseActivity extends AppCompatActivity {

    private static final String TAG = "AddExpenseActivity";
    private final FileController fc = new FileController();
    private static final boolean shouldAllowBack = false;

    private ArrayList<Expenses> expensesList;

    private Button mEnterButton;
    private Button mContinueButton;
    private EditText mExpenseNameEditText;
    private EditText mExpenseAmountEditText;
    private TextView mExpenseList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(bundle) called");
        setContentView(R.layout.activity_add_expense);

        mEnterButton = findViewById(R.id.expense_enter_button);
        mContinueButton = findViewById(R.id.expense_continue_button);
        mExpenseNameEditText = findViewById(R.id.expense_name);
        mExpenseAmountEditText = findViewById(R.id.expense_amount);
        mExpenseList = findViewById(R.id.expense_list);

        String expensesString = fc.readExpenses(getBaseContext());
        if(!expensesString.equals("null")){
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Expenses>>(){}.getType();
            expensesList = gson.fromJson(fc.readExpenses(getBaseContext()), listType);
            for (Expenses exp : expensesList){
                mExpenseList.append(exp.getName() + ": " + exp.getAmount() + "\n");
            }
        }else{
            expensesList = new ArrayList<>();
        }

        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mExpenseNameEditText.getText().toString();
                long amount = Long.parseLong(mExpenseAmountEditText.getText().toString());

                addExpenses(name, amount);
                Toast.makeText(getBaseContext(), "Added Bill " + name, Toast.LENGTH_SHORT).show();
                mContinueButton.setVisibility(View.VISIBLE);

                mExpenseAmountEditText.setText("");
                mExpenseNameEditText.setText("");
                mExpenseNameEditText.setFocusableInTouchMode(true);
                mExpenseNameEditText.requestFocus();

                mExpenseList.append(name + ": " + amount + "\n");
            }
        });

        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fc.writeExpenses(getBaseContext(), expensesList) == true){
                    Intent i = new Intent(AddExpenseActivity.this, MainActivity.class);
                    AddExpenseActivity.this.startActivity(i);
                }
            }
        });
    }

    public void addExpenses(String name, long amount) {
        this.expensesList.add(new Expenses(name, amount));
    }

    @Override
    public void onBackPressed() {
        if (!shouldAllowBack) {
        } else {
            super.onBackPressed();
        }
    }
}
