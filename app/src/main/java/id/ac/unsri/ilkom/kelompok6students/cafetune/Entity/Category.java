package id.ac.unsri.ilkom.kelompok6students.cafetune.Entity;

import java.util.ArrayList;

/**
 * Created by hp on 11/11/2017.
 */

public class Category {

    ArrayList list_categori;

    public Category(){
        this.list_categori = new ArrayList();
        this.list_categori.add(0,"Pop");
        this.list_categori.add(1,"Rock");
        this.list_categori.add(2,"Electronic");
        this.list_categori.add(3,"Classic");
        this.list_categori.add(4,"Jazz");
        this.list_categori.add(5,"Other");
    }

    public ArrayList getList_categori() {
        return list_categori;
    }

    public int getCategoryLength(){
        return this.list_categori.size();
    }

}
