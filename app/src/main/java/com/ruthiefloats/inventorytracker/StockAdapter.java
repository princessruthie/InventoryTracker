package com.ruthiefloats.inventorytracker;

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

import com.ruthiefloats.inventorytracker.model.Stock;

import java.util.ArrayList;
import java.util.Locale;


public class StockAdapter extends ArrayAdapter<Stock> {

    public static final String INTENT_STOCK = "intentStock";
    public StockAdapter(Context context, ArrayList<Stock> stocks){
        super(context, 0, stocks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_layout, parent, false);
        }
        final Stock currentStock = getItem(position);

        TextView name = (TextView) listItemView.findViewById(R.id.name);
        TextView price = (TextView) listItemView.findViewById(R.id.price);
        TextView quantity = (TextView) listItemView.findViewById(R.id.quantity);
        Button sellButton = (Button) listItemView.findViewById(R.id.sell_button);
        LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.clickable);

        name.setText(currentStock.getName());

        /*had to adjust this to get two decimal places */
        price.setText(String.format(Locale.getDefault(),"%.2f",currentStock.getPrice()));

        quantity.setText(String.valueOf(currentStock.getQuantity()));

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "item sold!", Toast.LENGTH_SHORT).show();
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(INTENT_STOCK, currentStock);
                getContext().startActivity(intent);
            }
        });

        return listItemView;
    }
}