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
    String college_id;
    String department_id;
    String teacher_id,comment;

    public Customlistadding() {

    }

    public Customlistadding(String name, String price, String code, String stock) {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Customlistadding(String name, String price, String code, String stock, String quantity, String college_id, String teacher_id, String department_id, String comment) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.stock = stock;
        this.quantity = quantity;
        this.college_id = college_id;
        this.teacher_id = teacher_id;
        this.department_id = department_id;
        this.comment = comment;

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


    public String getCollege_id() {
        return college_id;
    }

    public void setCollege_id(String college_id) {
        this.college_id = college_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }
}
