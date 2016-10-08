package com.example.ggladko97.razdor;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by ggladko97 on 03.05.16.
 */
public class Products {
    private float price;
    private String title;
    private int volume;
    private int quantity;

    public byte getImages() {
        return images;
    }

    public void setImages(byte images) {
        this.images = images;
    }

    private byte images;
   // private Image iv;


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
