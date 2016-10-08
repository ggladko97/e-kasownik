package com.example.ggladko97.razdor;

/**
 * Created by ggladko97 on 17.09.16.
 */
public class Orders {
    private double ordersCount=0.0;
    public Orders(){}
    public Orders(double ordersCount){
        this.ordersCount = ordersCount;
    }
    public double getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(double ordersCount) {
        this.ordersCount = ordersCount;
    }
}
