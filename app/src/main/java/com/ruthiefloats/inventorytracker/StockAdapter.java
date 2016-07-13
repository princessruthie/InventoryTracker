package com.ruthiefloats.inventorytracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ruthiefloats.inventorytracker.model.Stock;

import java.util.ArrayList;


public class StockAdapter extends ArrayAdapter<Stock> {
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
        Stock currentStock = getItem(position);

        TextView name = (TextView) listItemView.findViewById(R.id.name);
        TextView price = (TextView) listItemView.findViewById(R.id.price);
        TextView quantity = (TextView) listItemView.findViewById(R.id.quantity);
        // TODO: 7/13/16 for now the button doesn't change.  revisit this question
        // TODO: 7/13/16 also, if you change it, update the layout
//        Button sellButton = (Button) listItemView.findViewById(R.id.sell_button);


        name.setText(currentStock.getName());
        price.setText(String.valueOf(currentStock.getPrice()));
        quantity.setText(String.valueOf(currentStock.getQuantity()));

        return listItemView;
    }
}















