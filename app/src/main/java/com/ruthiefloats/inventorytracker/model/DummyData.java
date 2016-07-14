package com.ruthiefloats.inventorytracker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/13/16.
 */
public class DummyData {


    public static List<Stock> constructList(){
        List<Stock> mStockList = new ArrayList<>();
        mStockList.add(new Stock("blue pens", "great for writing blue things", 5, 2.25));
        mStockList.add(new Stock("black pens", "nice for writing black things", 7, 2.75));
        mStockList.add(new Stock("red pens", "bad for writing blue things", 300, 2.10));
        mStockList.add(new Stock("pink pens", "acceptable for writing love letters", 5, 2.30));
        mStockList.add(new Stock("brown pens", "good for drawing tree trunks", 4, 200));
        mStockList.add(new Stock("yellow pens", "not that great for anything", 10, 2));
        mStockList.add(new Stock("plain paper", "great for writing on", 20, 5));

        return mStockList;
    }
}
