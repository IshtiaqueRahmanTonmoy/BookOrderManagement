package info.androidhive.snackbar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonmoy on 10/23/16.
 */

public class CartSavingSqlite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "savingcart";
    private static final String TABLE_CART = "carttable";

    private static final String KEY_CID = "cid";
    private static final String KEY_BOOKNAME = "bookname";
    private static final String KEY_PRICE = "bookprice";
    private static final String KEY_CODE = "bookcode";
    private static final String KEY_STOCK = "stock";

    private SQLiteDatabase dbase;

    public CartSavingSqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_CART + " ("+ KEY_BOOKNAME + " TEXT, "+KEY_PRICE + " TEXT, "+KEY_CODE + " TEXT, " + KEY_STOCK + " TEXT )";
        dbase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addToCart(String name,String price,String code,String stock) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BOOKNAME,name);
        values.put(KEY_PRICE,price);
        values.put(KEY_CODE, code);
        values.put(KEY_STOCK,stock);
        // Inserting Row
        //dbase.insert(TABLE_QUEST, null, values);
        return db.insert(TABLE_CART, null, values);
    }

    public List<Customlistadding> getAllItems() {
        List<Customlistadding> quesList1 = new ArrayList<Customlistadding>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CART;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Customlistadding quest = new Customlistadding();
                quest.setName(cursor.getString(0));
                quest.setPrice(cursor.getString(1));
                quest.setCode(cursor.getString(2));
                quest.setStock(cursor.getString(3));
                quesList1.add(quest);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return quest list
        return quesList1;
    }
}
