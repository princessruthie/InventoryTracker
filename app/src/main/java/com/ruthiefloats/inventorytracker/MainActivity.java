package com.ruthiefloats.inventorytracker;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruthiefloats.inventorytracker.model.DummyData;
import com.ruthiefloats.inventorytracker.model.Stock;
import com.ruthiefloats.inventorytracker.tools.DBOpenHelper;
import com.ruthiefloats.inventorytracker.tools.StocksDataSource;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StocksDataSource dataSource = new StocksDataSource(this);
        dataSource.open();

        // TODO: 7/15/16 You can already make this better
        ArrayList<Stock> stocksNoId = (ArrayList<Stock>) DummyData.constructEmptyList();
        for (Stock stock: stocksNoId){
            dataSource.create(stock);
        }
        ArrayList<Stock> stocks = (ArrayList<Stock>) dataSource.findAll();

        dataSource.close();

        StockAdapter adapter = new StockAdapter(this, stocks);
        ListView listView = (ListView) findViewById(R.id.list);
        TextView addPrompt = (TextView) findViewById(R.id.add_prompt);

        if (adapter.getCount() == 0) {
            // TODO: 7/14/16 remember to set visibility again when
            // the Adapter has information
            listView.setVisibility(View.GONE);
            addPrompt.setVisibility(View.VISIBLE);

        } else {
            addPrompt.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(adapter);
        }
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
        } else
            return super.onOptionsItemSelected(item);
    }
}