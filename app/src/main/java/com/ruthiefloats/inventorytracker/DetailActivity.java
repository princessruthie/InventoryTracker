package com.ruthiefloats.inventorytracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruthiefloats.inventorytracker.adapter.StockAdapter;
import com.ruthiefloats.inventorytracker.model.Stock;
import com.ruthiefloats.inventorytracker.tools.StocksDataSource;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity {

    Stock currentStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView textView = (TextView) findViewById(R.id.detail_name);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                currentStock = extras.getParcelable(StockAdapter.INTENT_STOCK);
                textView.setText(currentStock.getName());
            }
        }

        ImageView imageView = (ImageView) findViewById(R.id.image);

        /*check that the imageUri actually isn't empty */
        if (!currentStock.getImageUri().equals("")) {
        }
        try {
            Uri imageUri = Uri.parse(currentStock.getImageUri());
            Bitmap bitmap = null;
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onSell(View view){
        StocksDataSource dataSource = new StocksDataSource(DetailActivity.this);
        boolean successfulSale = dataSource.decrementInventory(currentStock);
        if (successfulSale) {
            Toast.makeText(DetailActivity.this, "item sold!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(DetailActivity.this, "Can't have negative inventory.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onReceive(View view){
        Toast.makeText(DetailActivity.this, "One more added to inventory.", Toast.LENGTH_SHORT).show();
        StocksDataSource dataSource = new StocksDataSource(DetailActivity.this);
        dataSource.incrementInventory(currentStock);
    }

    public void onDelete(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        builder.setMessage("Do you really want to delete this?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StocksDataSource dataSource =
                                new StocksDataSource(DetailActivity.this);
                        dataSource.deleteRecord(currentStock);
                        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(DetailActivity.this, currentStock.getName() + " now deleted.", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*make sure after you cancel you end up in the same acty */
                        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                        startActivity(intent);
                        dialogInterface.cancel();
                    }
                }
        );

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void onOrder(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, "Ruthie.Floats@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Purchase Request");
        intent.putExtra(Intent.EXTRA_TEXT,
                "We only have " +
                        currentStock.getQuantity() +
                        " left in stock of the " +
                        currentStock.getName() +
                        ". Please send 100 more.");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}