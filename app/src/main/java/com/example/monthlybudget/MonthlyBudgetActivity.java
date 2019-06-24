package com.example.monthlybudget;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MonthlyBudgetActivity extends AppCompatActivity {
    private static final String TAG = "MonthlyBudgetActivity";
    private final FileController fc = new FileController();

    private Button mEnterButton;
    private EditText mMonthlyBudget;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_budget);
        final Context context = this.getBaseContext();


        mEnterButton = (Button)findViewById(R.id.budget_enter_button);

        mMonthlyBudget = (EditText)findViewById(R.id.monthly_budget_label);

        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long startingBudget = Long.MAX_VALUE;
                try{
                    startingBudget = Long.parseLong(mMonthlyBudget.getText().toString());
                    Log.d(TAG, "Converted Budget");
                }catch (Exception e){
                    Log.e(TAG, "Failed to Converted Budget");
                }

                if(fc.writeBudget(context, startingBudget) == true){
                    Log.d(TAG, "Leaving");
                    Intent i = new Intent(MonthlyBudgetActivity.this, BillsActivity.class);
                    MonthlyBudgetActivity.this.startActivity(i);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

}
