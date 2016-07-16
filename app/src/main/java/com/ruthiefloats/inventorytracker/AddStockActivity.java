package com.ruthiefloats.inventorytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ruthiefloats.inventorytracker.model.Stock;
import com.ruthiefloats.inventorytracker.tools.StocksDataSource;

public class AddStockActivity extends AppCompatActivity {

    private static final String TAG = "AddStockActivity";
    private static final int REQUEST_CODE = 255;
    String imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);
        Button addPicButton = (Button) findViewById(R.id.add_pic_button);
        Button submitNewStockButton = (Button) findViewById(R.id.submit_button);
        imageUri = "";

        addPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE);
            }
        });


        submitNewStockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStock();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            imageUri = String.valueOf(data.getData());
            Toast.makeText(this, imageUri, Toast.LENGTH_SHORT).show();
        }
    }


    private void addStock() {
        EditText nameEditText = (EditText) findViewById(R.id.add_name);
        EditText priceEditText = (EditText) findViewById(R.id.add_price);
        EditText quantityEditText = (EditText) findViewById(R.id.add_quantity);

        if (nameEditText.getText().toString().length() == 0) {
            nameEditText.setError("This can't be empty");
            Toast.makeText(this, "Uh-oh", Toast.LENGTH_SHORT).show();
            return;
        }

        if (priceEditText.getText().toString().length() == 0) {
            priceEditText.setError("This can't be empty");
            Toast.makeText(this, "Uh-oh", Toast.LENGTH_SHORT).show();
            return;
        }


        if (quantityEditText.getText().toString().length() == 0) {
            quantityEditText.setError("This can't be empty");
            Toast.makeText(this, "Uh-oh", Toast.LENGTH_SHORT).show();
            return;
        }

        if (imageUri == "") {
            Toast.makeText(this, "Please choose a picture!", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = String.valueOf(nameEditText.getText());
        double price = Double.parseDouble(priceEditText.getText().toString());
        int quantity = Integer.parseInt(quantityEditText.getText().toString());

        Stock stock = new Stock();
        stock.setName(name);
        stock.setPrice(price);
        stock.setQuantity(quantity);
        stock.setImageUri(imageUri);
        StocksDataSource dataSource = new StocksDataSource(this);
        dataSource.open();
        dataSource.create(stock);
        dataSource.close();
        Toast.makeText(this, "New item added", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}