package com.ruthiefloats.inventorytracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddStockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);

        Button addPicButton = (Button) findViewById(R.id.add_pic_button);
        Button submitNewStockButton = (Button) findViewById(R.id.submit_button);

        addPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddStockActivity.this, "Add pic", Toast.LENGTH_SHORT).show();
            }
        });

        submitNewStockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddStockActivity.this, "Submit New Stock Item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
