package com.example.monthlybudget;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity  {
    private final FileController fc = new FileController();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        if(fc.filesExist(getBaseContext())){
            Intent i = new Intent(LoadingActivity.this, MainActivity.class);
            LoadingActivity.this.startActivity(i);
        }else{
            Intent i = new Intent(LoadingActivity.this, MonthlyBudgetActivity.class);
            LoadingActivity.this.startActivity(i);
        }
}
}
