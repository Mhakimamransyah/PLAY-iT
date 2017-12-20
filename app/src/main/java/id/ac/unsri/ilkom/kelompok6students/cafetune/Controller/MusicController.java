package id.ac.unsri.ilkom.kelompok6students.cafetune.Controller;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import id.ac.unsri.ilkom.kelompok6students.cafetune.Database.MusicDB;
import id.ac.unsri.ilkom.kelompok6students.cafetune.Entity.Music;

/**
 * Created by hp on 26/11/2017.
 */


public class MusicController {

    MusicDB musicdb;

    public MusicController(Context context){
      this.musicdb = new MusicDB(context);
    }


    public void initiateAllMusic(ArrayList<Music> music){
//        musicdb.clear();
        musicdb.setAllMusic(music);
        musicdb.debugDataMusic();
    }

    public ArrayList<Music> getMusic(String genre){
        ArrayList<Music> music = musicdb.getData(genre);

        return music;
    }

    public boolean doRequest(int id,int state){
        boolean res = true;
        if(state == 1){
            if(musicdb.checkAccessRequest()){
                musicdb.changeStateRequsted(id,state);
                res = true;
            }
            else {
                res = false;
            }
        }
        else if(state == 0){
            musicdb.changeStateRequsted(id,state);
        }

        return res;
    }

    public void changeStateToUnclickable(int id) {
        musicdb.changeStateToUnclickable(id);
    }

    public int isClickable(int id) {
        return musicdb.isClickable(id);
    }

}
