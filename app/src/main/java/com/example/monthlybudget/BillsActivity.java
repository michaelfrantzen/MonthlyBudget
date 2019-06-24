package com.example.monthlybudget;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BillsActivity extends AppCompatActivity {
    private static final String TAG = "BillsActivity";
    private static ArrayList<Bills> billsList = new ArrayList<>();
    private final FileController fc = new FileController();

    private Button mEnterButton;
    private Button mContinueButton;
    private EditText mBillNameEditText;
    private EditText mBillAmountEditText;
    private TextView mBillList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(bundle) called");
        setContentView(R.layout.activity_bills);

        mEnterButton = findViewById(R.id.bills_enter_button);
        mContinueButton = findViewById(R.id.bills_continue_button);
        mBillAmountEditText = findViewById(R.id.bills_amount);
        mBillNameEditText = findViewById(R.id.bills_name);
        mBillList = findViewById(R.id.bills_list);

        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mBillNameEditText.getText().toString();
                long amount = Long.parseLong(mBillAmountEditText.getText().toString());

                addBills(name, amount);
                Toast.makeText(getBaseContext(), "Added Bill " + name, Toast.LENGTH_SHORT).show();
                mContinueButton.setVisibility(View.VISIBLE);

                mBillAmountEditText.setText("");
                mBillNameEditText.setText("");
                mBillNameEditText.setFocusableInTouchMode(true);
                mBillNameEditText.requestFocus();

                mBillList.append(name + ": " + amount + "\n");
            }
        });

        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fc.writeBills(getBaseContext(), billsList) == true){
                    Intent i = new Intent(BillsActivity.this, MainActivity.class);
                    BillsActivity.this.startActivity(i);
                }
            }
        });
    }

    public void addBills(String name, long amount) {
        this.billsList.add(new Bills(name, amount));
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
