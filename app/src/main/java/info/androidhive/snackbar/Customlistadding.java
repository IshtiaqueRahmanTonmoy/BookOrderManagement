package info.androidhive.snackbar;

import java.io.Serializable;

/**
 * Created by tonmoy on 10/19/16.
 */

public class Customlistadding
{
    int id;
    String name;
    String price;
    String code;
    String stock;
    String quantity;
    String totalsvalue;

        public Customlistadding(String name, String price, String code, String stock) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.stock = stock;
    }

    public Customlistadding(int id, String name, String price, String code, String stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.code = code;
        this.stock = stock;
    }

    public Customlistadding(int id, String name, String code, String stock, String quantity, String totalsvalue) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.stock = stock;
        this.quantity = quantity;
        this.totalsvalue = totalsvalue;
    }

    public Customlistadding() {

    }

    public Customlistadding(String name, String price, String code, String stock, String quantity) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.stock = stock;
        this.quantity = quantity;
    }

    public String getTotalsvalue() {
        return totalsvalue;
    }

    public void setTotalsvalue(String totalsvalue) {
        this.totalsvalue = totalsvalue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
