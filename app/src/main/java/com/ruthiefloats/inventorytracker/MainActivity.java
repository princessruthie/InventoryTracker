package com.ruthiefloats.inventorytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruthiefloats.inventorytracker.model.DummyData;
import com.ruthiefloats.inventorytracker.model.Stock;
import com.ruthiefloats.inventorytracker.tools.StocksDataSource;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    StocksDataSource dataSource;
    ListView listView;
    TextView addPrompt;
    StockAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource = new StocksDataSource(this);
        dataSource.open();

        ArrayList<Stock> stocks = (ArrayList<Stock>) dataSource.findAll();

        dataSource.close();

        adapter = new StockAdapter(this, stocks);
        listView = (ListView) findViewById(R.id.list);
        addPrompt = (TextView) findViewById(R.id.add_prompt);

        if (adapter.getCount() == 0) {
            showTextView();
        } else {
            showListView(adapter);
        }
    }

    private void showTextView() {
        listView.setVisibility(View.GONE);
        addPrompt.setVisibility(View.VISIBLE);
    }

    private void showListView(StockAdapter adapter) {
        addPrompt.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.the_only_menu_option) {
            Intent intent = new Intent(this, AddStockActivity.class);
            startActivity(intent);
            return true;
        } else {
            useDummyData();
            Toast.makeText(this, "Use dummy data", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private void useDummyData() {
        dataSource.open();

        ArrayList<Stock> stocksNoId = (ArrayList<Stock>) DummyData.constructList();
        ArrayList<Stock> stocksWithId = new ArrayList<>();
        for (Stock stock : stocksNoId) {
            stocksWithId.add(dataSource.create(stock));
        }
        ArrayList<Stock> allStocks = (ArrayList<Stock>) dataSource.findAll();

        dataSource.close();

        StockAdapter adapter = new StockAdapter(this, allStocks);
        listView = (ListView) findViewById(R.id.list);
        showListView(adapter);
    }

    /*
    if coming back into view, just rebuild it to reflect possible changes from the
    detail activity
     */
    @Override
    protected void onResume() {
        super.onResume();
        dataSource = new StocksDataSource(this);
        dataSource.open();

        ArrayList<Stock> stocks = (ArrayList<Stock>) dataSource.findAll();

        dataSource.close();

        adapter = new StockAdapter(this, stocks);
        listView = (ListView) findViewById(R.id.list);
        addPrompt = (TextView) findViewById(R.id.add_prompt);

        if (adapter.getCount() == 0) {
            showTextView();
        } else {
            showListView(adapter);
        }

    }
}