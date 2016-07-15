package com.ruthiefloats.inventorytracker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/13/16.
 */
public class DummyData {


    public static List<Stock> constructList(){
        List<Stock> mStockList = new ArrayList<>();
        mStockList.add(new Stock("Park 1 Photo", "how sweet", 3, 4.25, "park1"));
        mStockList.add(new Stock("Park 2 Photo", "great picture of a greate place", 5, 2.25, "park2"));
        mStockList.add(new Stock("Park 3 Photo", "pristine", 7, 2.75, "park3"));
        mStockList.add(new Stock("Park 4 Photo", "perfect lighting", 300, 2.10, "park4"));
        mStockList.add(new Stock("Park 5 Photo", "our most popular", 5, 2.30, "park5"));
        mStockList.add(new Stock("Park 6 Photo", "rarest", 4, 92, "park6"));
        mStockList.add(new Stock("Park 7 Photo", "this one isn't that great", 10, 2, "park7"));
        mStockList.add(new Stock("Park 8 Photo", "world famous", 20, 5, "park8"));

        return mStockList;
    }

    public static List<Stock> constructEmptyList(){
        List<Stock> mStockList = new ArrayList<>();
        return mStockList;
    }
}