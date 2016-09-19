package com.ruthiefloats.inventorytracker.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ruthiefloats.inventorytracker.DetailActivity;
import com.ruthiefloats.inventorytracker.R;
import com.ruthiefloats.inventorytracker.model.Stock;
import com.ruthiefloats.inventorytracker.tools.StocksDataSource;

import java.util.ArrayList;
import java.util.Locale;


public class StockAdapter extends ArrayAdapter<Stock> {

    public static final String INTENT_STOCK = "intentStock";

    public StockAdapter(Context context, ArrayList<Stock> stocks) {
        super(context, 0, stocks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_layout, parent, false);
        }
        final Stock currentStock = getItem(position);

        ViewHolder viewHolder = new ViewHolder();

        viewHolder.name = (TextView) listItemView.findViewById(R.id.name);
        viewHolder.price = (TextView) listItemView.findViewById(R.id.price);
        viewHolder.quantity = (TextView) listItemView.findViewById(R.id.quantity);
        viewHolder.sellButton = (Button) listItemView.findViewById(R.id.sell_button);
        viewHolder.linearLayout = (LinearLayout) listItemView.findViewById(R.id.clickable);

        viewHolder.name.setText(currentStock.getName());

        /*had to adjust this to get two decimal places */
        viewHolder.price.setText(String.format(Locale.getDefault(), "%.2f", currentStock.getPrice()));

        viewHolder.quantity.setText(String.valueOf(currentStock.getQuantity()));

        viewHolder.sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StocksDataSource dataSource = new StocksDataSource(getContext());
                boolean successfulSale = dataSource.decrementInventory(currentStock);
                if (successfulSale) {
                    Toast.makeText(getContext(), "Item sold!", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Can't have negative inventory.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(INTENT_STOCK, currentStock);
                getContext().startActivity(intent);
            }
        });

        return listItemView;
    }

    static class ViewHolder {
        TextView name;
        TextView price;
        TextView quantity;
        Button sellButton;
        LinearLayout linearLayout;
    }
}