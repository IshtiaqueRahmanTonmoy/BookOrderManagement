package info.androidhive.snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonmoy on 10/24/16.
 */

public class CartAddinglist implements Serializable{
    ArrayList<Customlistadding> list = new ArrayList<Customlistadding>();

    public CartAddinglist(int id,String name,String price,String code,String stock){
        list.add(new Customlistadding(id,name,price,code,stock));
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
