package com.ruthiefloats.inventorytracker.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Stock implements Parcelable {
    private String name;
    private String description;
    private int quantity;
    private long id;
    private double price;
    private Bitmap mBitmap;
    private String imageUri;

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public Stock(String name, String description, int quantity, double price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Stock(String name, String description, int quantity, double price, String imageUri) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.imageUri = imageUri;
    }

    public Stock(){}


    protected Stock(Parcel in) {
        name = in.readString();
        description = in.readString();
        quantity = in.readInt();
        id = in.readLong();
        price = in.readDouble();
        mBitmap = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
        imageUri = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(quantity);
        dest.writeLong(id);
        dest.writeDouble(price);
        dest.writeValue(mBitmap);
        dest.writeString(imageUri);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Stock> CREATOR = new Parcelable.Creator<Stock>() {
        @Override
        public Stock createFromParcel(Parcel in) {
            return new Stock(in);
        }

        @Override
        public Stock[] newArray(int size) {
            return new Stock[size];
        }
    };
}