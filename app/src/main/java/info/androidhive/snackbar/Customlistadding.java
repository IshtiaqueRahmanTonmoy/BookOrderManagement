package info.androidhive.snackbar;

/**
 * Created by tonmoy on 10/19/16.
 */

public class Customlistadding
{
    String name,price,code,stock,quantity;

    Customlistadding(String name,String price,String code,String stock)
    {
        this.name = name;
        this.price = price;
        this.code = code;
        this.stock = stock;
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
