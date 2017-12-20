package id.ac.unsri.ilkom.kelompok6students.cafetune.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import id.ac.unsri.ilkom.kelompok6students.cafetune.Entity.Music;

/**
 * Created by hp on 27/11/2017.
 */

public class MusicDB extends SQLiteOpenHelper {

    private static String DB_NAME = "mobcom_cafetune.db";
    private static String TABLE_NAME = "Music";
    private static String COL1 =  "Id";
    private static String COL2 =  "Judul";
    private static String COL3 =  "Tahun";
    private static String COL4 =  "Artist";
    private static String COL5 =  "Album";
    private static String COL6 =  "Genre";
    private static String COL7 =  "Req";

    public MusicDB(Context context) {
        super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+COL1+" INTEGER PRIMARY KEY,"+COL2+" TEXT,"+COL3+" INTEGER,"+COL4+" TEXT,"+COL5+" TEXT,"+COL6+
                " TEXT,"+COL7+" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABEL IF EXIST "+TABLE_NAME);
        onCreate(db);
    }

    public void setAllMusic(ArrayList<Music> music){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Music> musicRequestedInPhone = this.getAllRequestedMusicInPhone();
        ContentValues cv;
        int state,j;

        this.clear();
        for(int i=0;i<music.size();i++){
            cv = new ContentValues();
            state = 0;
            j = 0;
            while(j<musicRequestedInPhone.size()){
                if(music.get(i).getId_music() == musicRequestedInPhone.get(j).getId_music()) {
                    state = 1;
                    break;
                }
                j++;
            }
            cv.put(COL1,music.get(i).getId_music());
            cv.put(COL2,music.get(i).getJudul());
            cv.put(COL3,music.get(i).getTahun());
            cv.put(COL4,music.get(i).getArtist());
            cv.put(COL5,music.get(i).getAlbum());
            cv.put(COL6,music.get(i).getGenre());
            cv.put(COL7,state);
            db.insert(TABLE_NAME,null,cv);
        }
    }

    public ArrayList<Music> getData(String genre){
        ArrayList<Music> music = new ArrayList<Music>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL6+" = '"+genre+"'",null);

        Music m;
        int i=0;
        while(data.moveToNext()){
            m = new Music(data.getString(1).toString(),data.getString(2).toString(),data.getString(3).toString(),data.getString(4).toString(),
                    data.getString(5).toString(),Integer.parseInt(data.getString(0).toString()),Integer.parseInt(data.getString(6).toString()));
            music.add(i,m);
            i++;
        }
        Log.d("genji","jingoklah "+music.size());
        return music;
    }

    public void changeStateRequsted(int id,int state){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE "+TABLE_NAME+" SET "+COL7+" = "+state+" WHERE "+COL1+" = "+id;
        db.execSQL(sql);
    }

    public ArrayList<Music> getAllRequestedMusicInPhone(){
        ArrayList<Music> music = new ArrayList<Music>();
        SQLiteDatabase db = this.getWritableDatabase();
        Music m;

        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+COL7+" = 1";
        Cursor data = db.rawQuery(sql,null);
        while(data.moveToNext()){
            m = new Music(data.getString(1).toString(),data.getString(2).toString(),data.getString(3).toString(),data.getString(4).toString(),
                    data.getString(5).toString(),Integer.parseInt(data.getString(0).toString()),Integer.parseInt(data.getString(6).toString()));
            music.add(m);
        }

        return music;
    }

    public boolean checkAccessRequest(){
        boolean res = true;
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+COL7+" = 1";
        Cursor data = db.rawQuery(sql,null);
        if(data.getCount() == 3){
            res = false;
        }

        return  res;

    }



    public void clear(){
//        TODO : memperbarui perpustakaan dengan mengosongkan secara menyeluruh
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
    }

    public void debugDataMusic(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        Log.d("danang","panjang "+data.getCount());
        while(data.moveToNext()){
            Log.d("cukong","Judul : "+data.getString(1));
            Log.d("cukong","Tahun : "+data.getString(2));
            Log.d("cukong","Artis : "+data.getString(3));
            Log.d("cukong","Album : "+data.getString(4));
            Log.d("cukong","Genre : "+data.getString(5));
            Log.d("cukong","ID : "+data.getString(0));
        }
    }
}
