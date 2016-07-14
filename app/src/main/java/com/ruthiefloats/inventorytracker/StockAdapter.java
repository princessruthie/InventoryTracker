package com.ruthiefloats.inventorytracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ruthiefloats.inventorytracker.model.Stock;

import java.util.ArrayList;
import java.util.Locale;


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
        LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.clickable);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
            }
        });


        name.setText(currentStock.getName());
        /*had to adjust this to get two decimal places */
        price.setText(String.format(Locale.getDefault(),"%.2f",currentStock.getPrice()));
        quantity.setText(String.valueOf(currentStock.getQuantity()));

        return listItemView;
    }
}















