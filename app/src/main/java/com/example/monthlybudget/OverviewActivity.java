package com.example.monthlybudget;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class OverviewActivity extends AppCompatActivity {
    private static final String TAG = "OverviewActivity";
    private final FileController fc = new FileController();

    private TextView mList;
    private ImageButton mResetWeek;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(bundle) called");
        setContentView(R.layout.activity_spending_overview);

        mList = findViewById(R.id.list_items);
        mResetWeek = findViewById(R.id.reset_button);

        mResetWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fc.writeExpenses(getBaseContext(), null);
            }
        });

    }

    public void onRadioButtonClick(View view){
        boolean isChecked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.bills_choice:
                if(isChecked){
                    mList.setText("");
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Bills>>(){}.getType();
                    List<Bills> bills = gson.fromJson(fc.readBills(getBaseContext()), listType);
                    for (Bills bill : bills){
                        mList.append(bill.getName() + ": " + bill.getAmount() + "\n");
                    }
                }
                break;
            case R.id.expenses_choice:
                if(isChecked){
                    mList.setText("");
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Expenses>>(){}.getType();
                    if(!fc.readExpenses(getBaseContext()).equals("null")){
                        Log.e(TAG, "Null");
                        List<Expenses> expensesList = gson.fromJson(fc.readExpenses(getBaseContext()), listType);
                        for (Expenses exp : expensesList){
                            mList.append(exp.getName() + ": " + exp.getAmount() + "\n");
                        }
                    }else{
                        mList.setText("No Expenses");
                    }

                }
                break;
        }
    }
}
