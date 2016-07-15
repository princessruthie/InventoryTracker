package com.ruthiefloats.inventorytracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ruthiefloats.inventorytracker.model.Stock;
import com.ruthiefloats.inventorytracker.tools.StocksDataSource;

public class AddStockActivity extends AppCompatActivity {

    private static final String TAG = "AddStockActivity";

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
                addStock();
            }
        });
    }

    private void addStock(){
        EditText nameEditText = (EditText) findViewById(R.id.add_name);
        EditText priceEditText = (EditText) findViewById(R.id.add_price);
        EditText quantityEditText = (EditText) findViewById(R.id.add_quantity);

        String name = String.valueOf(nameEditText.getText());
        double price = Double.parseDouble(priceEditText.getText().toString());
        int quantity = Integer.parseInt(quantityEditText.getText().toString());

        Stock stock = new Stock();
        stock.setName(name);
        stock.setPrice(price);
        stock.setQuantity(quantity);
        //todo come back to actually vary this.
        stock.setImage("park9");

        StocksDataSource dataSource = new StocksDataSource(this);
        dataSource.open();
        dataSource.create(stock);
        dataSource.close();
    }
}