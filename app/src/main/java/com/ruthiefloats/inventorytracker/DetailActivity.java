package com.ruthiefloats.inventorytracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView textView = (TextView) findViewById(R.id.detail_name);

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras != null){
                String productName = extras.getString(StockAdapter.PRODUCT_NAME_EXTRA);
                textView.setText(productName);
            }
        } else{
            textView.setText("yikes");
        }

        Button sellButton = (Button) findViewById(R.id.sell_button);
        Button receiveButton = (Button) findViewById(R.id.receive_button);
        Button orderButton = (Button) findViewById(R.id.order_button);
        Button deleteButton = (Button) findViewById(R.id.delete_button);

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "Sell Button", Toast.LENGTH_SHORT).show();
            }
        });

        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "Receive Button", Toast.LENGTH_SHORT).show();
            }
        });

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "Order Button", Toast.LENGTH_SHORT).show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "Delete Button", Toast.LENGTH_SHORT).show();
            }
        });
    }
}