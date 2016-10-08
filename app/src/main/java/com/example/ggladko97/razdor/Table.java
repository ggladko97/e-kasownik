package com.example.ggladko97.razdor;

import android.content.Intent;

/**
 * Created by ggladko97 on 15.09.16.
 */
public class Table {
    public short getNumber() {
        return number;
    }

    private short number = 0;

    public int getPeopleCount() {
        return peopleCount;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public int getOrderID() {
        return orderID;
    }

    public double getPriceForTable() {
        return priceForTable;
    }

    private int peopleCount = 0;
    private boolean isEmpty = true;
    private int orderID;
    private double priceForTable=0.0;

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    private Intent intent;

    public Table(short number, int peopleCount, boolean isEmpty, int orderID, Intent intent){
        this.number = number;
        this.peopleCount = peopleCount;
        this.isEmpty = isEmpty;
        this.orderID = orderID;
        this.intent = intent;
    }
    public Table(short number, int peopleCount, boolean isEmpty, int orderID, double priceForTable, Intent intent){
        this.number = number;
        this.peopleCount = peopleCount;
        this.isEmpty = isEmpty;
        this.orderID = orderID;
        this.priceForTable=priceForTable;
        this.intent = intent;
    }
//    public static Table valueOf(short number, int peopleCount, boolean isEmpty, int orderID){
//        return new Table(number,peopleCount,isEmpty,orderID, intent);//static factory
//    }
    public void setPriceForTable(double priceForTable){
        this.priceForTable = priceForTable;
    }
    public void test(){}


}
