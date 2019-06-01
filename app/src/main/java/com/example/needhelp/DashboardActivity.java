package com.example.needhelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.practice.R;

public class DashboardActivity extends AppCompatActivity {

    Button btn_addwork, btn_searchwork;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        
        btn_addwork=findViewById(R.id.btn_addwork);
        btn_searchwork=findViewById(R.id.btn_searchwork);
        
        
        btn_addwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardActivity.this, WorkActivity.class);
                startActivity(intent);
            }
        });


        btn_searchwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
