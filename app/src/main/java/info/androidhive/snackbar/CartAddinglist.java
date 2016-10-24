package info.androidhive.snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonmoy on 10/24/16.
 */

public class CartAddinglist {
    ArrayList<Customlistadding> list = new ArrayList<Customlistadding>();

    public CartAddinglist(String name,String price,String code,String stock){
        list.add(new Customlistadding(name,price,code,stock));
    }

    public CartAddinglist() {

    }

    public void addCart(List<Customlistadding>list){
        this.list = (ArrayList<Customlistadding>) list;
    }

    public List<Customlistadding> getCart(){
        return this.list;
    }
}
