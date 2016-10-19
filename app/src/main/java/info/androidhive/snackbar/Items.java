package info.androidhive.snackbar;

/**
 * Created by tonmoy on 10/16/16.
 */

public class Items {
    String name,stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    Items(String name, String stock)
    {
        this.name = name;
        this.stock = stock;
    }

}
