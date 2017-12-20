package id.ac.unsri.ilkom.kelompok6students.cafetune;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by hp on 26/09/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final  String DATABASE_N = "Coba.db";
    private static final  String TABLE_N    = "Latihan";
    private static final  String COL_1   = "Id";
    private static final  String COL_2    = "Judul";
    private static final  String COL_3    = "Deksripsi";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_N,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_N +" (Id INTEGER PRIMARY KEY AUTOINCREMENT , Judul TEXT , Deksripsi TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_N);
        onCreate(db);
    }

    public long insertData(String judul,String desc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues obj = new ContentValues();
        obj.put(COL_2,judul);
        obj.put(COL_3,desc);
        long result =  db.insert(TABLE_N,null,obj);
        return result;
    }

    public ArrayList<Model> getAllListData(){
        ArrayList<Model> model = new ArrayList<Model>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_N,null);
//        model.add(new Model(res.getString(res.getColumnIndex(COL_2)),res.getString(res.getColumnIndex(COL_3))));
        while(res.moveToNext()){
            model.add(new Model(res.getString(1),res.getString(2)));
        }

        return model;
    }
}
